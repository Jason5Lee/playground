// original: https://fsharpforfunandprofit.com/posts/concurrency-reactive/
import { merge, partition, Observable, Subscriber } from "rxjs";
import { pairwise, map } from "rxjs/operators";

const delta = 100;
const simulateTime = 5000;

interface FizzBuzzEvent {
    label: number;
    now: number;
}

function createStarterAndObservable(label: number): [() => void, Observable<FizzBuzzEvent>] {
    const subscribers: Subscriber<FizzBuzzEvent>[] = [];
    const observable: Observable<FizzBuzzEvent> = new Observable(subscriber => { subscribers.push(subscriber); });
    function start(): void {
        const id = setInterval(() => {
            for (const subscriber of subscribers) {
                subscriber.next({
                    label: label,
                    now: Date.now(),
                });
            }
        }, label * delta);
        setTimeout(() => clearInterval(id), simulateTime);
    };
    return [start, observable];
}

function areSimultaneous([earlier, later]: [FizzBuzzEvent, FizzBuzzEvent]): boolean {
    const { now: now1 } = earlier;
    const { now: now2 } = later;
    return now2 - now1 < delta / 2;
}

const [start3, eventStream3] = createStarterAndObservable(3);
const [start5, eventStream5] = createStarterAndObservable(5);

const combinedStream = merge(eventStream3, eventStream5);

const pairwiseStream = combinedStream.pipe(pairwise());

const [simultaneousStream, nonSimultaneousStream] =
    partition(pairwiseStream, areSimultaneous);

const [fizzStream, buzzStream] =
    partition(
        nonSimultaneousStream.pipe(map(v => v[0])),
        ({ label: id }) => id == 3);

combinedStream
    .subscribe(({ label: id, now: t }) =>
        console.log(`[${id}] ${t}`));

simultaneousStream
    .subscribe(() => console.log("FizzBuzz"));

fizzStream.subscribe(() => console.log("Fizz"));

buzzStream.subscribe(() => console.log("Buzz"));

start3(); start5();
