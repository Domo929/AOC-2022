package day14

import Day
import shared.Grid

class Day14(path: String) : Day(path) {
    override fun solve(): Pair<Any, Any> {
        return Pair(part1(parse(lines)), part2(parse(lines)))
    }

    // parse returns a list of every position that is rock from the input
    private fun parse(lines: List<String>): Grid<Char> {
        val rockVerticesPerLine = mutableListOf<MutableList<Pair<Int, Int>>>()
        for (line in lines) {
            val rockLine = mutableListOf<Pair<Int, Int>>()
            val verticesStr = line.split(" -> ")
            for (vStr in verticesStr) {
                val v = vStr.split(",").map { it.toInt() }
                rockLine.add(Pair(v[0], v[1]))
            }
            rockVerticesPerLine.add(rockLine)
        }

        val rocks = mutableSetOf<Pair<Int, Int>>()
        for (rockLine in rockVerticesPerLine) {
            for (i in 0 until rockLine.size - 1) {
                val v1 = rockLine[i]
                val v2 = rockLine[i + 1]

                val x1 = v1.first
                val y1 = v1.second
                val x2 = v2.first
                val y2 = v2.second

                if (x1 == x2) {
                    for (y in lesser(y1, y2)..greater(y1, y2)) {
                        rocks.add(Pair(x1, y))
                    }
                } else {
                    for (x in lesser(x1, x2)..greater(x1, x2)) {
                        rocks.add(Pair(x, y1))
                    }
                }
            }
        }

        val grid = Grid(1000, 500, '.', '#')
        for (rock in rocks) {
            grid.add('#', rock.first, rock.second)
        }

        return grid
    }

    private fun lesser(a: Int, b: Int): Int {
        return if (a < b) a else b
    }

    private fun greater(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    private fun part1(grid: Grid<Char>): Int {
        var totalSand = 0
        while(true) { // until we are in perpetual freefall
            val sand = placeSand(grid) ?: return totalSand
            grid.add('o', sand.first, sand.second)
            totalSand++
        }
    }

    private fun placeSand(grid: Grid<Char>): Pair<Int, Int>? {
        var currentPos = Pair(500, 0)

        while(true) {
            val coordsToCheck = getCoordinatesToCheck(currentPos)
            var allFilled = true
            for(coord in coordsToCheck) {
                if (grid.isFreefall(coord)) {
                    return null
                }
                if (grid.isEmpty(coord)) {
                    currentPos = coord
                    allFilled = false
                    break
                }
            }
            if (allFilled) {
                return currentPos
            }
        }
    }

    private fun getCoordinatesToCheck(start: Pair<Int, Int>): List<Pair<Int, Int>> {
        return listOf(
            Pair(start.first, start.second + 1),
            Pair(start.first - 1, start.second + 1),
            Pair(start.first + 1, start.second + 1)
        )
    }

    private fun part2(grid: Grid<Char>): Int {
        grid.addFloor(grid.findDeepestY()+2)

        var totalSand = 0
        while(true) { // until we are in perpetual freefall
            val sand = placeSand(grid)!!
            grid.add('o', sand.first, sand.second)
            totalSand++

            if (sand == Pair(500, 0)) {
                return totalSand
            }
        }
    }
}