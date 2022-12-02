import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

class Day2Test {

    @Test
    fun testData() {
        Day2("src/test/resources/day2/test.txt").solve().let {
            assertEquals(15, it.first)
            assertEquals(12, it.second)
        }
    }

    @Test
    fun testReal(){
        Day2("src/test/resources/day2/input.txt").solve().let {
            println(it.first)
            println(it.second)

            assertTrue(true)
        }
    }
}