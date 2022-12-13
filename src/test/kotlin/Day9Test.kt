import day09.Day9
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun testData() {
        Day9("src/test/resources/day9/test.txt").solve().let {
            assertEquals(13, it.first)
            assertEquals(1, it.second)
        }
    }

    @Test
    fun testReal() {
        Day9("src/test/resources/day9/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}