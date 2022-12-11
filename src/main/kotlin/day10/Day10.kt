package day10

import Day
class Day10(path : String): Day(path) {
    override fun solve(): Pair<Any, Any> {
        val commands = parseCommands(lines)

        return Pair(part1(commands), part2(commands))
    }

    private fun parseCommands(lines: List<String>): List<Pair<String, Int?>> {
        val commands: MutableList<Pair<String, Int?>> = mutableListOf()
        for (line in lines) {
            if (line == "noop") {
                commands.add(Pair(line, null))
                continue
            }
            val parts = line.split(" ")
            val amount = parts[1].toInt()

            commands.add(Pair(parts[0], amount))
            commands.add(Pair("execute", amount))
        }

        return commands
    }

    private fun part1(commands: List<Pair<String, Int?>>): Int {
        var sum = 0
        var x = 1

        for((index, command) in commands.withIndex()) {
            val tick = index + 1
            if ( (tick - 20) % 40 == 0 ) {
                val signal = tick * x
                sum += signal

                println("tick: $tick | x: $x | signal: $signal | sum: $sum")
            }

            if (command.first == "execute") {
                x += command.second!!
            }
        }

        return sum
    }

    private fun part2(commands: List<Pair<String, Int?>>) {
        val display: MutableList<String> = mutableListOf()
        var x = 1
        for ((position, command) in commands.withIndex()) {
            //draw
            if (position%40 in x-1..x+1){
                display.add("##")
            } else {
                display.add("..")
            }

            if (command.first == "execute") {
                x += command.second!!
            }
        }

        for(i in 0 until display.size step 40) {
            println(display.subList(i, i+40).reduce{line, char -> line + char})
        }
    }

}