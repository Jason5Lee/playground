import { isPrime } from "../src/index";
import * as assert from "assert";

for (const num of [2.7, 3.6, 4.5]) {
    test(num + " should not be prime", () => {
        assert.equal(isPrime(num), false);
    });
}

for (const num of [-1, 0, 1]) {
    test(num + " should not be prime", () => {
        assert.equal(isPrime(num), false);
    });
}

for (const num of [2, 3, 5, 7, 11]) {
    test(num + " should be prime", () => {
        assert.equal(isPrime(num), true);
    });
}

for (const num of [4, 6, 8, 9]) {
    test(num + " should not be prime", () => {
        assert.equal(isPrime(num), false);
    });
}
