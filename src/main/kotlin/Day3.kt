class Day3(path: String) : Day(path) {
    private val letters: String = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private val score: HashMap<Char, Int> = hashMapOf()
    init {
        for ((index, ch) in letters.iterator().withIndex()) {
            score[ch] = index + 1
        }
    }

    override fun solve(): Pair<Int, Int> {
        val part1Answer = lines.map { scoreLinePart1(it) }.reduce { sum, element -> sum + element }

        val part2Answer = lines.withIndex()
            .groupBy { it.index / 3 }
            .map { scoreGroupPart2(it.value) }
            .reduce { sum, element -> sum + element }

        return Pair(part1Answer, part2Answer)
    }

    private fun scoreLinePart1(line: String): Int {
        val containerLeft = line.substring(0, line.length / 2)
        val containerLeftContains = containerLeft.toSet()

        val containerRight = line.substring(line.length / 2, line.length)
        val shared = mutableSetOf<Char>()
        var lineScore = 0
        for (ch in containerRight.iterator()) {
            if (containerLeftContains.contains(ch)) {
                shared.add(ch)
            }
        }

        for (ch in shared.iterator()) {
            lineScore += score[ch]!!
        }


        return lineScore
    }

    private fun scoreGroupPart2(lines: List<IndexedValue<String>>): Int {
        if (lines.size != 3) {
            throw Exception("Invalid group size")
        }
        val common = lines[0].value.toSet()
            .intersect(lines[1].value.toSet())
            .intersect(lines[2].value.toSet())

        return score[common.first()]!!
    }

}