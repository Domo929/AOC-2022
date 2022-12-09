import day3.Day3
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    @DisplayName("Confirm it passes test data")
    fun testData() {
        Day3("src/test/resources/day3/test.txt").solve().let {
            assertEquals(157, it.first)
            assertEquals(70, it.second)
        }
    }

    @Test
    fun testReal() {
        Day3("src/test/resources/day3/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}