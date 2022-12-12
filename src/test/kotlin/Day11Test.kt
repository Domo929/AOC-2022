import day11.Day11
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun testData() {
        Day11("src/test/resources/day11/test.txt").solve().let {
            assertEquals(10605L, it.first)
            assertEquals(2713310158L, it.second)
        }
    }

    @Test
    fun testReal() {
        Day11("src/test/resources/day11/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}