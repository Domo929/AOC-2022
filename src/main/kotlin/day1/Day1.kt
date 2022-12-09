package day1

import Day

class Day1(path: String) : Day(path) {
    override fun solve(): Pair<Int, Int> {
        val elves: MutableList<MutableList<Int>> = mutableListOf()
        var elf: MutableList<Int> = mutableListOf()
        for (line in lines) {
            if (line.isEmpty()) {
                elves.add(elf)
                elf = mutableListOf()
                continue
            }

            elf.add(line.toInt())
        }
        elves.add(elf)

        val calories: MutableList<Int> = mutableListOf()
        for (e in elves) {
            calories.add(e.sum())
        }

        val sortedCalories = calories.sortedBy { it }.asReversed()

        val part1Answer = sortedCalories[0]

        val part2Answer = sortedCalories.subList(0, 3).sum()

        return Pair(part1Answer, part2Answer)
    }
}