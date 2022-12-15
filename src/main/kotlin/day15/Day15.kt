package day15

import Day

class Day15(path: String) : Day(path) {
    override fun solve(): Pair<Any, Any> {
        // this day's input varies for test/real data, so we can't use the base solve method for both test and real data
        return Pair(0,0)
    }

    private fun parse(lines: List<String>): List<Sensor> {
        val sensors = mutableListOf<Sensor>()
        val match = Regex("=(-*\\d+)")

        for (line in lines) {
            val positions = match.findAll(line).map { it.value.substring(1).toInt() }.toList()
            sensors.add(Sensor(Pair(positions[0], positions[1]), Pair(positions[2], positions[3])))
        }

        return sensors
    }

    private fun part1(sensors: List<Sensor>, yWeCareAbout: Int): Int {
        val covered = mutableSetOf<Int>()
        for (sensor in sensors) {
            val sensorCoverage = sensor.allPossibleXs(yWeCareAbout)
            for (x in sensorCoverage) {
                covered.add(x)
            }
        }
        // remove all locations where a beacon actually is
        for (sensor in sensors) {
            if (sensor.getBeaconPosition().second == yWeCareAbout) {
                covered.remove(sensor.getBeaconPosition().first)
            }
        }

        return covered.size
    }

    fun solvePart1(yWeCareAbout: Int): Int {
        return part1(parse(lines), yWeCareAbout)
    }

    fun solvePart2(min: Int, max: Int): Long {
        val sensors = parse(lines)

        val overlap = shared.MergeOverlappingIntervals()

        for (y in min..max) {
            val ranges = mutableListOf<Pair<Int, Int>>()
            for (sensor in sensors) {
                sensor.allPossibleXsList(y, min, max)?.let { ranges.add(it) }
            }

            val mergedRanges = overlap.mergeIntervals(ranges)

            if (mergedRanges.size != 1) {
                val x = mergedRanges[0].second+1
                return x * 4000000L + y
            }

//            println("checked $y")
        }

        return 0
    }
}