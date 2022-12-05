package day5

import Day

class Day5(path: String) : Day(path) {
    override fun solve(): Pair<String, String> {
        return Pair(
            Ship(lines).executePart1Commands(),
            Ship(lines).executePart2Commands()
        )
    }


}



