package day15

import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class SensorTest{
    @Test
    fun testSensor(){
        val sensor = Sensor(Pair(8,7), Pair(2,10))

        var positions = sensor.allPossibleXs(7)
        assert(positions.size == 19)
        for(x in -1..17) {
            if (x == 8) {
                continue
            }
            assertContains(positions,x)
        }

        positions = sensor.allPossibleXs(2)
        for(x in 4..12) {
            if (x == 8) {
                continue
            }
            assertContains(positions, x)
        }
    }
}