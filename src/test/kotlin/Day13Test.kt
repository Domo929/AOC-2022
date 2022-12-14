import day13.Day13
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun testData() {
        Day13("src/test/resources/day13/test.txt").solve().let {
            assertEquals(13, it.first)
            assertEquals(140, it.second)
        }
    }

    @Test
    fun testReal() {
        Day13("src/test/resources/day13/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}