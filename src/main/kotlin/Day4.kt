class Day4(path: String) : Day(path) {

    override fun solve(): Pair<Int, Int> {
        val pairs: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableListOf()
        for (line in lines) {
            pairs.add(split(line))
        }

        return Pair(solvePart(pairs, isTotalOverlap), solvePart(pairs, isSemiOverlap))
    }

    private fun split(line: String): Pair<Pair<Int, Int>, Pair<Int, Int>> {
        val (e1, e2) = line.split(",")

        val (e1sl, e1sh) = e1.split("-").map { it.toInt() }
        val e1p = Pair(e1sl, e1sh)

        val (e2sl, e2sh) = e2.split("-").map { it.toInt() }
        val e2p = Pair(e2sl, e2sh)

        return Pair(e1p, e2p)
    }

    private fun solvePart(
        elfPairs: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
        f: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean
    ): Int {
        return elfPairs.map { f(it.first, it.second) }.map {
            if (it) {
                1
            } else {
                0
            }
        }.reduce { sum, element -> sum + element }
    }


    private val isTotalOverlap: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean =
        { e1: Pair<Int, Int>, e2: Pair<Int, Int> ->
            e1.first <= e2.first && e1.second >= e2.second || e1.first >= e2.first && e1.second <= e2.second
        }

    private val isSemiOverlap: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean =
        { e1: Pair<Int, Int>, e2: Pair<Int, Int> ->
            e1.second >= e2.first && e1.first <= e2.second
        }
}