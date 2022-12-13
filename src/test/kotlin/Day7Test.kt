import day07.Day7
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    @DisplayName("Confirm it passes test data")
    fun testData() {
        Day7("src/test/resources/day7/test.txt").solve().let {
            assertEquals(95437, it.first)
            assertEquals(24933642, it.second)
        }
    }

    @Test
    fun testReal() {
        Day7("src/test/resources/day7/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}