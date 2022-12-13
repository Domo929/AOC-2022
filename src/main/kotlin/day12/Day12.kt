package day12

import Day
import java.util.PriorityQueue
import kotlin.math.abs

class Day12(path: String) : Day(path) {
    private val charToScore: HashMap<Char, Int> = hashMapOf()

    init {
        for ((index, c) in "abcdefghijklmnopqrstuvwxyz".withIndex()) {
            charToScore[c] = index
        }
        charToScore['S'] = charToScore['a']!!
        charToScore['E'] = charToScore['z']!!
    }

    override fun solve(): Pair<Any, Any> {
        val nodes = parse(lines)



        return Pair(part1(nodes), part2(nodes))
    }

    private fun part1(nodes: List<List<Node>>): Int {
        val start = findPos('S', nodes)[0]
        val end = findPos('E', nodes)[0]

        val h = { n: Node -> abs(n.y - end.y) + abs(n.x - end.x) }

        return AStar(start, end, h, nodes).size - 1
    }

    private fun part2(nodes: List<List<Node>>): Int {
        val starts = findPos('S', nodes) + findPos('a', nodes)
        val end = findPos('E', nodes)[0]

        val h = { n: Node -> abs(n.y - end.y) + abs(n.x - end.x) }

        var least = Int.MAX_VALUE
        for (start in starts) {
            try {
                val score = AStar(start, end, h, nodes).size - 1
                if (score < least) {
                    least = score
                }
            } catch (_: java.lang.Exception) {
                // who cares we'll find at least one guaranteed
            }

        }

        return least
    }

    private fun parse(lines: List<String>): List<List<Node>> {
        val rows: MutableList<MutableList<Node>> = mutableListOf()

        for ((yNdx, line) in lines.withIndex()) {
            val nodes: MutableList<Node> = mutableListOf()

            for ((xNdx, c) in line.withIndex()) {
                nodes.add(Node(xNdx, yNdx, c, charToScore[c]!!))
            }
            rows.add(nodes)
        }

        return rows
    }

    private fun reconstructPath(cameFrom: HashMap<Node, Node>, start: Node): List<Node> {
        val path: ArrayDeque<Node> = ArrayDeque()
        path.addFirst(start)

        var current = start
        while (current in cameFrom.keys) {
            current = cameFrom[current]!!
            path.addFirst(current)
        }

        return path
    }

    private fun findPos(letter: Char, nodes: List<List<Node>>): List<Node> {
        val pos: MutableList<Node> = mutableListOf()
        for (row in nodes) {
            for (n in row) {
                if (n.letter == letter) {
                    pos.add(n)
                }
            }
        }

        return pos
    }

    private fun getValidNeighbors(current: Node, nodes: List<List<Node>>): List<Node> {
        val validNeighbors: MutableList<Node> = mutableListOf()

        // check right
        if (current.x != nodes[0].size - 1) {
            val possibleNode = nodes[current.y][current.x + 1]
            if (possibleNode.score <= current.score + 1) {
                validNeighbors.add(possibleNode)
            }
        }

        // check below
        if (current.y < nodes.size - 1) {
            val possibleNode = nodes[current.y + 1][current.x]
            if (possibleNode.score <= current.score + 1) {
                validNeighbors.add(possibleNode)
            }
        }

        // check left
        if (current.x > 0) {
            val possibleNode = nodes[current.y][current.x - 1]
            if (possibleNode.score <= current.score + 1) {
                validNeighbors.add(possibleNode)
            }
        }

        // check above
        if (current.y > 0) {
            val possibleNode = nodes[current.y - 1][current.x]
            if (possibleNode.score <= current.score + 1) {
                validNeighbors.add(possibleNode)
            }
        }

        return validNeighbors
    }

    private fun inPQ(pq: PriorityQueue<Pair<Int, Node>>, isIn: Node): Boolean {
        for (n in pq) {
            if (n.second == isIn) {
                return true
            }
        }
        return false
    }

    private fun AStar(start: Node, end: Node, h: (Node) -> Int, nodes: List<List<Node>>): List<Node> {
        val compareNodeScores: Comparator<Pair<Int, Node>> = compareBy { it.first }
        val pq: PriorityQueue<Pair<Int, Node>> = PriorityQueue(compareNodeScores)

        pq.add(Pair(h(start), start))

        val cameFrom: HashMap<Node, Node> = hashMapOf()

        val gScore: HashMap<Node, Int> = hashMapOf()
        gScore[start] = 0

        val fScore: HashMap<Node, Int> = hashMapOf()
        fScore[start] = h(start)

        while (pq.size > 0) {
            val current = pq.peek().second
            if (current == end) {
                return reconstructPath(cameFrom, current)
            }
            pq.remove()

            for (neighbor in getValidNeighbors(current, nodes)) {
                val tentativeGScore = gScore[current]!! + 1
                if (tentativeGScore < gScore.getOrDefault(neighbor, Int.MAX_VALUE)) {
                    cameFrom[neighbor] = current
                    gScore[neighbor] = tentativeGScore

                    fScore[neighbor] = tentativeGScore + h(neighbor)

                    if (!inPQ(pq, neighbor)) {
                        pq.add(Pair(fScore[neighbor]!!, neighbor))
                    }
                }
            }

        }

        throw Exception("Not found")
    }

}