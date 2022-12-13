import day04.Day4
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    @DisplayName("Confirm it passes test data")
    fun testData() {
        Day4("src/test/resources/day4/test.txt").solve().let {
            assertEquals(2, it.first)
            assertEquals(4, it.second)
        }
    }

    @Test
    fun testReal() {
        Day4("src/test/resources/day4/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}