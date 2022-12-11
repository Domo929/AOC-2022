import day10.Day10
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun testData() {
        Day10("src/test/resources/day10/test.txt").solve().let {
            assertEquals(13140, it.first)
        }
    }

    @Test
    fun testReal() {
        Day10("src/test/resources/day10/input.txt").solve().let {
            println(it.first)

            assertTrue(true)
        }
    }
}