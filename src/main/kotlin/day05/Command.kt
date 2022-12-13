package day05

class Command(line: String) {
    private var amount: Int = 0
    private var from: Int = 0
    private var to: Int = 0

    init {
        val parts = line.split(" ")
        if (parts.size != 6) {
            throw Exception("Invalid size")
        }

        amount = parts[1].toInt()
        from = parts[3].toInt()
        to = parts[5].toInt()
    }

    fun getAmount(): Int {
        return amount
    }

    fun getFrom(): Int {
        return from
    }

    fun getTo(): Int {
        return to
    }
}