package mathSet

interface MathSet {
    val size: Double
}

interface Intersectable<T> {
    fun intersect(rhs: T): T
}

fun <T> inclusiveExclusive(sets: List<T>): Double
        where T : MathSet, T : Intersectable<T> {
    fun intersectLoop(acc: T, index: Int): Double = if (index >= sets.size) {
        acc.size
    } else {
        intersectLoop(acc, index + 1) - intersectLoop(acc.intersect(sets[index]), index + 1)
    }
    return sets.withIndex().map { intersectLoop(it.value, it.index + 1) }.sum()
}