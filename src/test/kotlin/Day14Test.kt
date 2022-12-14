import day14.Day14
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun testData() {
        Day14("src/test/resources/day14/test.txt").solve().let {
            assertEquals(24, it.first)
            assertEquals(93, it.second)
        }
    }

    @Test
    fun testReal() {
        Day14("src/test/resources/day14/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}