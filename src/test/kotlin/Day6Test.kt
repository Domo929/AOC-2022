import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    @DisplayName("Confirm it passes test data")
    fun testData() {
        Day6("src/test/resources/day6/test.txt").solve().let {
            assertEquals(7, it.first)
            assertEquals(19, it.second)
        }
    }

    @Test
    fun testReal() {
        Day6("src/test/resources/day6/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}