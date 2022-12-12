package day11

class Monkey(lines: List<String>) {
    private var items: ArrayDeque<Long> = ArrayDeque()

    private var operation: (Long) -> Long = { 0 }

    private var test: Long = 0

    private var trueMonkey: Int = 0
    private var falseMonkey: Int = 0

    private var inspectCount: Long = 0

    init {
        // starting items
        for (i in lines[1].split(":")[1].trim().split(",")) {
            items.addLast(i.trim().toLong())
        }

        // operation
        val opsList = lines[2].split("=")[1].trim().split(" ")
        var second: Long = 0
        var isOld = false
        if (opsList[2] == "old") {
            isOld = true
        } else {
            second = opsList[2].toLong()
        }

        var operationMethod: (Long, Long) -> Long = { _: Long, _: Long -> 0 }
        when (opsList[1]) {
            "*" -> {
                operationMethod = { x: Long, y: Long -> x * y }
            }

            "+" -> {
                operationMethod = { x: Long, y: Long -> x + y }
            }
        }

        operation = if (isOld) {
            { old: Long -> operationMethod(old, old) }
        } else {
            { old: Long -> operationMethod(old, second) }
        }

        // test
        test = lines[3].split(" ").last().toLong()

        // true monkey
        trueMonkey = lines[4].split(" ").last().toInt()

        // false monkey
        falseMonkey = lines[5].split(" ").last().toInt()
    }

    fun getInspectCount(): Long {
        return inspectCount
    }

    fun addItem(item: Long) {
        items.addLast(item)
    }

    fun getTest(): Long {
        return test
    }

    fun keepAwayDivide(divideAmount: Long): List<Pair<Int, Long>> {
        return keepAway { score: Long -> score / divideAmount }
    }

    fun keepAwayMod(modAmount: Long): List<Pair<Int, Long>> {
        return keepAway { score: Long -> score % modAmount }
    }

    private fun keepAway(reduceMethod: (Long) -> Long): List<Pair<Int, Long>> {
        val thrown: MutableList<Pair<Int, Long>> = mutableListOf()

        for (item in items) {
            inspectCount++

            val newScore = reduceMethod(operation(item))

            if (newScore % test == 0L) {
                thrown.add(Pair(trueMonkey, newScore))
            } else {
                thrown.add(Pair(falseMonkey, newScore))
            }
        }
        items = ArrayDeque()

        return thrown
    }
}