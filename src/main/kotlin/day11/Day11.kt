package day11

import Day

class Day11(path: String) : Day(path) {
    override fun solve(): Pair<Any, Any> {
        val monkeys = parse(lines)

        var modProduct = 1L
        for (m in monkeys) {
            modProduct *= m.getTest()
        }

        return Pair(
            keepAwayForRounds(parse(lines), 20, 3, false),
//            10605,
            keepAwayForRounds(parse(lines), 10000, modProduct, true)
//            2713310158
        )
    }

    private fun parse(lines: List<String>): List<Monkey> {
        val emptyLines: MutableList<Int> = mutableListOf(-1)
        for ((index, line) in lines.withIndex()) {
            if (line == "") {
                emptyLines.add(index)
            }
        }

        val monkeys: MutableList<Monkey> = mutableListOf()

        for (i in 0 until emptyLines.size - 1) {
            monkeys.add(Monkey(lines.subList(emptyLines[i] + 1, emptyLines[i + 1])))
        }
        monkeys.add(Monkey(lines.subList(emptyLines.last() + 1, lines.size)))


        return monkeys
    }

    private fun keepAwayForRounds(monkeys: List<Monkey>, rounds: Int, reduceAmount: Long, isMod: Boolean): Long {
        for (i in 0 until rounds) {
            val round = i + 1

            for (m in monkeys) {
                val itemsAndToWho: List<Pair<Int, Long>> = if (isMod) {
                    m.keepAwayMod(reduceAmount)
                } else {
                    m.keepAwayDivide(reduceAmount)
                }
                for (item in itemsAndToWho) {
                    monkeys[item.first].addItem(item.second)
                }
            }

            if (round % 1000 == 0 || round == 1 || round == 20) {
                println("Round $round")
                for ((index, m) in monkeys.withIndex()) {
                    println("Monkey $index: count ${m.getInspectCount()}")
                }
            }
        }

        val inspectCounts: MutableList<Long> = mutableListOf()
        for (m in monkeys) {
            inspectCounts.add(m.getInspectCount())
        }
        inspectCounts.sortDescending()

        return inspectCounts[0] * inspectCounts[1]
    }
}