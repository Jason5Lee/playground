//! Functional sieve method for finding primes.

struct Primes {
    iter: Option<Box<Iterator<Item = u64>>>,
}
impl Primes {
    fn new() -> Primes {
        Primes {
            iter: Some(Box::new(2..)),
        }
    }
}
impl Iterator for Primes {
    type Item = u64;

    fn next(&mut self) -> Option<u64> {
        let mut iter = self.iter.take()?;
        let result = iter.next();
        if let Some(prime) = result {
            self.iter = Some(Box::new(iter.filter(move |n| n % prime != 0)));
        }
        result
    }
}

#[test]
fn tests() {
    assert_eq!(
        Primes::new().take(16).collect::<Vec<_>>(),
        vec![2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53]
    );
}
