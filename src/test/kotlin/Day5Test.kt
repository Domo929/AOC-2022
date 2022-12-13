import day05.Day5
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    @DisplayName("Confirm it passes test data")
    fun testData() {
        Day5("src/test/resources/day5/test.txt").solve().let {
            assertEquals("CMZ", it.first)
            assertEquals("MCD", it.second)
        }
    }

    @Test
    fun testReal() {
        Day5("src/test/resources/day5/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}