package day7

import Day

class Day7(path: String) : Day(path) {
    override fun solve(): Pair<Any, Any> {
        val rootNode = parseTree(lines)

        return Pair(solvePart1(rootNode), solvePart2(rootNode))
    }

    private fun parseTree(lines: List<String>): Node {
        val root: Node = DirNode("/", null)
        var current: Node = root
        for ((index, line) in lines.withIndex()) {
            if (index == 0) {
                continue
            }
            val parts = line.split(" ")
            when (parts[0]) {
                "$" -> {
                    when(parts[1]) {
                        "cd" -> {
                            current.cd(parts[2])?.let {
                                current = it
                            } ?: throw Exception("No such directory")
                        }
                        "ls" -> {
                            // the else case will handle adding files to the current directory
                            continue
                        }
                    }
                }
                "dir" -> {
                    current.add(DirNode(parts[1], current))
                }
                else -> {
                    current.add(FileNode(parts[1], current, parts[0].toInt()))
                }
            }
        }

        return root
    }

    private fun solvePart1(root: Node): Int {
        val dirs = root.getDirectories()
        var sum = 0
        for(dir in dirs) {
            val dirSize = dir.size()
            if (dirSize <= 100000) {
                sum += dirSize
            }
        }

        return sum
    }

    private fun solvePart2(root: Node): Int {
        val dirs = root.getDirectories()
        val totalSize = root.size()
        val freeSpace = 70000000 - totalSize
        val requiredDeleteSize = 30000000 - freeSpace

        val dirSizes = mutableListOf<Int>()
        for (dir in dirs) {
            dirSizes.add(dir.size())
        }
        dirSizes.sort()
        for (dirSize in dirSizes) {
            if (dirSize >= requiredDeleteSize) {
                return dirSize
            }
        }

        return 0
    }
}