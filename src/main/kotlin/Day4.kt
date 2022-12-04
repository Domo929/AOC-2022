class Day4(path: String) : Day(path) {

    override fun solve(): Pair<Int, Int> {
        val pairs: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableListOf()
        for (line in lines) {
            val (e1, e2) = line.split(",")

            val (e1sl, e1sh) = e1.split("-")
            val e1p = Pair(e1sl.toInt(), e1sh.toInt())

            val (e2sl, e2sh) = e2.split("-")
            val e2p = Pair(e2sl.toInt(), e2sh.toInt())

            pairs.add(Pair(e1p, e2p))
        }

        return Pair(solvePart(pairs, isTotalOverlap), solvePart(pairs, isSemiOverlap))
    }

    private fun solvePart(
        elfPairs: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
        f: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean
    ): Int {
        return elfPairs.map { f(it.first, it.second) }.map { toInt(it) }.reduce { sum, element -> sum + element }
    }


    private val isTotalOverlap: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean =
        { e1: Pair<Int, Int>, e2: Pair<Int, Int> ->
            e1.first <= e2.first && e1.second >= e2.second || e1.first >= e2.first && e1.second <= e2.second
        }

    private val isSemiOverlap: (Pair<Int, Int>, Pair<Int, Int>) -> Boolean =
        { e1: Pair<Int, Int>, e2: Pair<Int, Int> ->
               e1.second >= e2.first && e1.first <= e2.second
        }

    private fun toInt(b: Boolean): Int {
        if (b) {
            return 1
        }
        return 0
    }
}