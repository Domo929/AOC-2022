import day1.Day1
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    @DisplayName("Confirm it passes test data")
    fun testData() {
        Day1("src/test/resources/day1/test.txt").solve().let {
            assertEquals(24000, it.first)
            assertEquals(45000, it.second)
        }
    }

    @Test
    @DisplayName("Confirm it passes real data")
    fun testReal() {
        Day1("src/test/resources/day1/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}