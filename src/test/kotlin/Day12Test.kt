import day12.Day12
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun testData() {
        Day12("src/test/resources/day12/test.txt").solve().let {
            assertEquals(31, it.first)
            assertEquals(29, it.second)
        }
    }

    @Test
    fun testReal() {
        Day12("src/test/resources/day12/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}