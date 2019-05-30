package mathSet

import kotlin.test.*

class TestMathSet {
    class PositiveIntegersUnder1000DividedBy(val n: Int): MathSet,
        Intersectable<PositiveIntegersUnder1000DividedBy> {

        companion object {
            tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
            fun lcm(a: Int, b: Int) = a / gcd(a, b) * b
        }

        override val size: Double = (1000 / n).toDouble()

        override fun intersect(rhs: PositiveIntegersUnder1000DividedBy): PositiveIntegersUnder1000DividedBy =
                PositiveIntegersUnder1000DividedBy(lcm(n, rhs.n))

    }

    @Test
    fun testInclusiveExclusive() {
        val l = (2..10).map { PositiveIntegersUnder1000DividedBy(it) }.toList()
        val expect = (1..1000)
            .filter { num ->
                l.any { num % it.n == 0 }
            }
            .count()
            .toDouble()
        val actual = inclusiveExclusive(l)
        assertEquals(expect, actual)
    }
}