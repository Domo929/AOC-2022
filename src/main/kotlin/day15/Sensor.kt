package day15

import kotlin.math.abs

class Sensor(private val position: Pair<Int, Int>, private val beaconPosition: Pair<Int, Int>) {
    private fun distance(): Int {
        return abs(position.first - beaconPosition.first) + abs(position.second - beaconPosition.second)
    }

    fun allPossibleXs(yWeCareAbout: Int): Set<Int> {
        val dist = distance()

        val positions = mutableSetOf<Int>()

        val yDist = abs(position.second - yWeCareAbout)
        if (yDist >= dist) {
            return positions
        }

        val leftRange = position.first - (dist - yDist)
        val rightRange = position.first + (dist - yDist)
        for (x in leftRange..rightRange) {
            positions.add(x)
        }

        return positions

    }

    fun allPossibleXsList(
        yWeCareAbout: Int,
        xMin: Int,
        xMax: Int
    ): Pair<Int, Int>? {
        val dist = distance()


        val yDist = abs(position.second - yWeCareAbout)
        if (yDist >= dist) {
            return null
        }

        var rangeLow = position.first - (dist - yDist)
        if (rangeLow < xMin) {
            rangeLow = xMin
        }
        var rangeHigh = position.first + (dist - yDist)
        if (rangeHigh > xMax) {
            rangeHigh = xMax
        }

        return Pair(rangeLow, rangeHigh)
    }


    fun getBeaconPosition(): Pair<Int, Int> {
        return beaconPosition
    }
}