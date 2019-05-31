//! Church encoding

trait Church {
    fn iterate<T>(&self, f: &mut impl FnMut(T) -> T, x: T) -> T;
}

#[derive(Copy, Clone)]
struct Zero {}
impl Church for Zero {
    fn iterate<T>(&self, _: &mut impl FnMut(T) -> T, x: T) -> T {
        x
    }
}

#[derive(Copy, Clone)]
struct Succ<N: Church>(N);
impl<N: Church> Church for Succ<N> {
    fn iterate<T>(&self, f: &mut impl FnMut(T) -> T, x: T) -> T {
        let fx = f(x);
        self.0.iterate(f, fx)
    }
}

fn to_usize(n: impl Church) -> usize {
    n.iterate(&mut |x| x + 1, 0)
}

fn pow(base: usize, expo: impl Church) -> usize {
    expo.iterate(&mut |v| v * base, 1)
}

#[test]
fn tests() {
    let zero = Zero {};
    assert_eq!(to_usize(zero), 0);
    let one = Succ(zero);
    assert_eq!(to_usize(one), 1);
    let three = Succ(Succ(one));
    assert_eq!(pow(2, three), 8);
}
