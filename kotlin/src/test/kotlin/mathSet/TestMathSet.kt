package mathSet

import kotlin.test.*

class TestMathSet {
    class PositiveIntegersUnder1000DividedBy(val divisor: Int) : MathSet,
        Intersectable<PositiveIntegersUnder1000DividedBy> {

        companion object {
            tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
            fun lcm(a: Int, b: Int) = a / gcd(a, b) * b
        }

        override val size: Double = (1000 / divisor).toDouble()

        override fun intersect(rhs: PositiveIntegersUnder1000DividedBy): PositiveIntegersUnder1000DividedBy =
            PositiveIntegersUnder1000DividedBy(lcm(divisor, rhs.divisor))
    }

    @Test
    fun testInclusiveExclusive() {
        val l = (2..10).map { PositiveIntegersUnder1000DividedBy(it) }.toList()
        val expect = (1..1000)
            .filter { num ->
                l.any { num % it.divisor == 0 }
            }
            .count()
            .toDouble()
        val actual = inclusiveExclusive(l)
        assertEquals(expect, actual)
    }
}