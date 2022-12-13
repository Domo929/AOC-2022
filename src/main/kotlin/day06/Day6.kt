package day06

import Day

class Day6(path: String) : Day(path) {

    override fun solve(): Pair<Any, Any> {
        if (lines.size != 1) {
            throw Exception("Invalid input")
        }

        return Pair(findNDistinct(lines[0], 4), findNDistinct(lines[0], 14))
    }

    private fun findNDistinct(line: String, n: Int): Int {
        for (i in n until line.toList().size) {
            if (line.substring(i - n, i).toSet().size == n) {
                return i
            }
        }

        return -1
    }
}