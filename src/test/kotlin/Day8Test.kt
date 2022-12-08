import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun testData() {
        Day8("src/test/resources/day8/test.txt").solve().let {
            assertEquals(21, it.first)
            assertEquals(8, it.second)
        }
    }

    @Test
    fun testReal() {
        Day8("src/test/resources/day8/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}