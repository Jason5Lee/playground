export function is_prime(n: 2.7 | 3.6 | 4.5): false
export function is_prime(n: 1 | 0 | -1): false
export function is_prime(n: 2 | 3 | 5 | 7 | 11): true
export function is_prime(n: 4 | 6 | 8 | 9): false
export function is_prime(n: number): boolean
export function is_prime(n: number): boolean {
    if (!Number.isInteger(n) || n < 2) {
        return false;
    }
    let sqrt = Math.sqrt(n);
    for (let i = 2; i <= sqrt; ++i) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}
