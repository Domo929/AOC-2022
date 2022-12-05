package day5

class Ship(lines: List<String>) {
    private val crates: MutableList<ArrayDeque<Char>> = mutableListOf()
    private val commands: MutableList<Command> = mutableListOf()

    init {
        val splitIndex = findSplitIndex(lines)

        val crateLines = lines.subList(0, splitIndex - 1)
        val size = findSize(lines[splitIndex - 1])
        makeCrates(size)

        val commandLines = lines.subList(splitIndex + 1, lines.size)

        for (line in crateLines) {
            for (i in 1 until (size * 4) step 4) {
                val crate = line.getOrNull(i)
                if (crate == ' ' || crate == null) {
                    continue
                }
                val crateIndex = convertIndex(i)
                addCrateTopToBottom(crate, crateIndex)
            }
        }

        addCommands(commandLines)
    }

    // UTILITY FUNCTIONS
    private fun findSplitIndex(lines: List<String>): Int {
        for ((index, line) in lines.withIndex()) {
            if (line == "") {
                return index
            }
        }
        return -1
    }


    private fun convertIndex(index: Int): Int {
        return ((index - 1) / 4) + 1
    }

    private fun findSize(line: String): Int {
        val nums: List<Int> = line.trim().split("\\s+".toRegex()).map { it.toInt() }
        return nums.last()
    }

    private fun makeCrates(size: Int) {
        for (i in 1..size) {
            crates.add(ArrayDeque())
        }
    }

    private fun getTops(): String {
        return crates.map { it.last() }.joinToString("")
    }

    // CRATE FUNCTIONS

    // Add crate to the top of the stack, physically impossible
    // but this is how we get the test input
    private fun addCrateTopToBottom(crate: Char, at: Int) {
        crates[at - 1].addFirst(crate)
    }

    private fun moveSingleCrate(command: Command) {
        for (i in 0 until command.getAmount()) {
            crates[command.getTo() - 1].addLast(crates[command.getFrom() - 1].removeLast())
        }
    }

    private fun moveMultipleCrates(command: Command) {
        val toMove: MutableList<Char> = mutableListOf()
        for (i in 0 until command.getAmount()) {
            toMove.add(crates[command.getFrom() - 1].removeLast())
        }

        for (i in 0 until command.getAmount()) {
            crates[command.getTo() - 1].addLast(toMove.removeLast())
        }
    }

    // COMMAND FUNCTIONS

    private fun addCommands(commandLines: List<String>) {
        for (line in commandLines) {
            commands.add(Command(line))
        }
    }

    fun executePart1Commands(): String {
        for (command in commands) {
            moveSingleCrate(command)
        }

        return getTops()
    }

    fun executePart2Commands(): String {
        for (command in commands) {
            moveMultipleCrates(command)
        }
        return getTops()
    }
}