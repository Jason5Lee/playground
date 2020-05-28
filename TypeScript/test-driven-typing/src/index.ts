export function isPrime(n: 2.7 | 3.6 | 4.5): false
export function isPrime(n: 1 | 0 | -1): false
export function isPrime(n: 2 | 3 | 5 | 7 | 11): true
export function isPrime(n: 4 | 6 | 8 | 9): false
export function isPrime(n: number): boolean
export function isPrime(n: number): boolean {
    if (!Number.isInteger(n) || n < 2) {
        return false;
    }
    const sqrt = Math.sqrt(n);
    for (let i = 2; i <= sqrt; ++i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}
