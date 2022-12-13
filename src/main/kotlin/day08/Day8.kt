package day08

import Day

class Day8(path: String) : Day(path) {

    override fun solve(): Pair<Any, Any> {
        val trees = parse(lines)

        return Pair(solvePart1(trees), solvePart2(trees))
    }

    private fun parse(lines: List<String>): List<List<Int>> {
        val rows: MutableList<List<Int>> = mutableListOf()

        for (line in lines) {
            val row: MutableList<Int> = mutableListOf()
            for (char in line) {
                row.add(char.toString().toInt())
            }
            rows.add(row)
        }

        return rows
    }

    private fun solvePart1(trees: List<List<Int>>): Int {
        var sum = 0

        val checked: HashMap<Pair<Int, Int>, Boolean> = hashMapOf()

        //check rows
        for ((rowIndex, row) in trees.withIndex()) {
            val cols = countHeightsBothDirections(row)
            for (col in cols) {
                if (checked[Pair(rowIndex, col)] == null) {
                    sum += 1
                    checked[Pair(rowIndex, col)] = true
                }
            }
        }

        //check cols
        for (colIndex in 0 until trees[0].size) {
            val col = trees.map { it[colIndex] }
            val rows = countHeightsBothDirections(col)
            for (row in rows) {
                if (checked[Pair(row, colIndex)] == null) {
                    sum += 1
                    checked[Pair(row, colIndex)] = true
                }
            }
        }

        return sum
    }

    private fun solvePart2(trees: List<List<Int>>): Int {
        var most = 0
        for ((y, row) in trees.withIndex()) {
            for ((x, _) in row.withIndex()) {
                val score = countCardinalDirections(trees, x, y)
                if (score > most) {
                    most = score
                }
            }
        }

        return most
    }

    private fun countHeightsBothDirections(row: List<Int>): List<Int> {
        val heightIndices: MutableList<Int> = mutableListOf(0)
        var height = row[0]
        for (i in 1 until row.size) {
            if (row[i] > height) {
                heightIndices.add(i)
                height = row[i]
            }
        }
        heightIndices.add(row.size - 1)
        height = row[row.size - 1]
        for (i in row.size - 2 downTo 0) {
            if (row[i] > height) {
                heightIndices.add(i)
                height = row[i]
            }
        }

        return heightIndices
    }

    private fun countCardinalDirections(trees: List<List<Int>>, startX: Int, startY: Int): Int {
        val refHeight = trees[startY][startX]

        var northDistance = 0
        for (i in startY - 1 downTo 0) {
            northDistance += 1
            if (trees[i][startX] >= refHeight) {
                break
            }
        }

        var eastDistance = 0
        for (i in startX - 1 downTo 0) {
            eastDistance += 1
            if (trees[startY][i] >= refHeight) {
                break
            }
        }

        var southDistance = 0
        for (i in startY + 1 until trees[0].size) {
            southDistance += 1
            if (trees[i][startX] >= refHeight) {
                break
            }
        }

        var westDistance = 0
        for (i in startX + 1 until trees.size) {
            westDistance += 1
            if (trees[startY][i] >= refHeight) {
                break
            }
        }

        return eastDistance * westDistance * northDistance * southDistance
    }
}