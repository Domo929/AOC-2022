import day15.Day15
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Timeout
import java.util.concurrent.TimeUnit

class Day15Test {

    @Test
    fun testData() {
        assertEquals(26, Day15("src/test/resources/day15/test.txt").solvePart1(10))
        assertEquals(56000011, Day15("src/test/resources/day15/test.txt").solvePart2(0, 20))
    }

    @Test
    @Timeout(value = 6, unit = TimeUnit.HOURS)
    fun testReal() {
        println(Day15("src/test/resources/day15/input.txt").solvePart1(2000000))
        println(Day15("src/test/resources/day15/input.txt").solvePart2(0, 4000000))

        assert(true)
    }
}