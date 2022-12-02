class Day2(path: String) : Day(path) {

    // outcomes
    private val lost = 0
    private val won = 6
    private val draw = 3

    // played
    private val rock = 1
    private val paper = 2
    private val scissors = 3

    override fun solve(): Pair<Int, Int> {
        val commands: List<Pair<String, String>> = lines.map { it.split(" ") }.map { Pair(it[0], it[1]) }

        val part1Score = commands.map { scorePart1(it) }.reduce { sum, element -> sum + element }

        val part2Score = commands.map { scorePart2(it) }.reduce { sum, element -> sum + element }

        return Pair(part1Score, part2Score)
    }

    private fun scorePart1(command: Pair<String, String>): Int {
        val (opponent, you) = command
        when (opponent) {
            "A" -> { // rock
                when (you) {
                    "X" -> return rock + draw // rock
                    "Y" -> return paper + won // paper
                    "Z" -> return scissors + lost // scissors
                }
            }

            "B" -> { // paper
                when (you) {
                    "X" -> return rock + lost // rock
                    "Y" -> return paper + draw // paper
                    "Z" -> return scissors + won // scissors
                }
            }

            "C" -> { // scissors
                when (you) {
                    "X" -> return rock + won // rock
                    "Y" -> return paper + lost // paper
                    "Z" -> return scissors + draw // scissors
                }
            }
        }
        throw Exception("Invalid command")
    }

    private fun scorePart2(command: Pair<String, String>): Int {
        val (opponent, outcome) = command
        when (opponent) {
            "A" -> { // rock
                when (outcome) {
                    "X" -> return scissors + lost // lose
                    "Y" -> return rock + draw // draw
                    "Z" -> return paper + won // win
                }
            }

            "B" -> { // paper
                when (outcome) {
                    "X" -> return rock + lost // lose
                    "Y" -> return paper + draw // draw
                    "Z" -> return scissors + won // win
                }
            }

            "C" -> { // scissors
                when (outcome) {
                    "X" -> return paper + lost // lose
                    "Y" -> return scissors + draw // draw
                    "Z" -> return rock + won // win
                }
            }
        }
        throw Exception("Invalid command")
    }
}