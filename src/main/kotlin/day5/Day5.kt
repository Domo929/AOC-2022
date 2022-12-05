package day5

import Day

class Day5(path: String) : Day(path) {
    override fun solve(): Pair<String, String> {
        val part1Ship = Ship(lines)
        part1Ship.executePart1Commands()
        val part1tops = part1Ship.getTops()

        val part2Ship = Ship(lines)
        part2Ship.executePart2Commands()
        val part2tops = part2Ship.getTops()


        return Pair(part1tops, part2tops)
    }


}



