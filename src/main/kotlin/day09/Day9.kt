package day09

import Day
import kotlin.math.abs
import kotlin.math.max

class Day9(path: String) : Day(path) {
    override fun solve(): Pair<Any, Any> {
        val commands = parse(lines)

        return Pair(trackMovements(commands, 2, false), trackMovements(commands, 10, false))
    }

    private fun parse(lines: List<String>): List<Pair<Char, Int>> {
        val commands: MutableList<Pair<Char, Int>> = mutableListOf()

        for (line in lines) {
            val command = line.split(" ")
            commands.add(Pair(command[0].toCharArray()[0], command[1].toInt()))

        }

        return commands
    }

    private fun trackMovements(commands: List<Pair<Char, Int>>, numberOfKnots: Int, printDebug: Boolean): Int {
        val tailMovementLocations: MutableSet<Pair<Int, Int>> = mutableSetOf(Pair(0, 0))

        val knots: MutableList<Pair<Int, Int>> = (1..numberOfKnots).map { Pair(0, 0) }.toMutableList()

        for (command in commands) {
            for (i in 0 until command.second) {
                var headPosition = knots[0]
                when (command.first) {
                    'U' -> headPosition = Pair(headPosition.first, headPosition.second + 1)
                    'D' -> headPosition = Pair(headPosition.first, headPosition.second - 1)
                    'L' -> headPosition = Pair(headPosition.first - 1, headPosition.second)
                    'R' -> headPosition = Pair(headPosition.first + 1, headPosition.second)
                }
                knots[0] = headPosition
                for (knotIndex in 1 until knots.size) {
                    var tailPosition = knots[knotIndex]

                    val xDist = abs(headPosition.first - tailPosition.first)
                    val xHigher = headPosition.first > tailPosition.first

                    val yDist = abs(headPosition.second - tailPosition.second)
                    val yHigher = headPosition.second > tailPosition.second

                    val dist = max(xDist, yDist)

                    if (dist > 1) {
                        // if they're on the same column
                        if (headPosition.first == tailPosition.first) {
                            tailPosition = if (yHigher) {
                                Pair(tailPosition.first, tailPosition.second + 1)
                            } else {
                                Pair(tailPosition.first, tailPosition.second - 1)
                            }
                            // if they're on the same row
                        } else if (headPosition.second == tailPosition.second) {
                            tailPosition = if (xHigher) {
                                Pair(tailPosition.first + 1, tailPosition.second)
                            } else {
                                Pair(tailPosition.first - 1, tailPosition.second)
                            }
                            // they're diagonal
                        } else {
                            tailPosition = if (xHigher && yHigher) {
                                Pair(tailPosition.first + 1, tailPosition.second + 1)
                            } else if (xHigher) { // we already know yHigher is false
                                Pair(tailPosition.first + 1, tailPosition.second - 1)
                            } else if (yHigher) { // we already know that xHigher is false
                                Pair(tailPosition.first - 1, tailPosition.second + 1)
                            } else { // we know they're both false
                                Pair(tailPosition.first - 1, tailPosition.second - 1)
                            }
                        }
                    }
                    if (knotIndex == knots.size - 1) {
                        tailMovementLocations.add(tailPosition)
                    }
                    headPosition = tailPosition
                    knots[knotIndex] = tailPosition
                }
            }
            if (printDebug) {
                println("command: $command")
                for ((index, knot) in knots.withIndex()) {
                    println("knot $index: $knot")
                }
            }
        }

        return tailMovementLocations.size
    }
}