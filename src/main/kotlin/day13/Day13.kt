package day13

import Day
import java.lang.Exception

class Day13(path: String) : Day(path) {
    val log = false

    override fun solve(): Pair<Any, Any> {
        return Pair(part1(parse(lines)), part2(parse(lines)))
    }

    private fun parse(lines: List<String>): List<Pair<Packet, Packet>> {
        val sublists = lines.withIndex().groupBy { it.index / 3 }.values
        val packetPairs: MutableList<Pair<Packet, Packet>> = mutableListOf()

        for (sublist in sublists) {
            val packet1 = parseLine(sublist[0].value)
            val packet2 = parseLine(sublist[1].value)
            packetPairs.add(Pair(packet1, packet2))
        }

        return packetPairs
    }

    private fun parseLine(line: String): Packet {
        val (pkt, rem) = parsePacketList(line)
        if (rem.isNotEmpty()) {
            throw IllegalArgumentException("Invalid input: $line")
        }
        return pkt
    }

    private fun parsePacketList(line: String): Pair<ListPacket, String> {
        if (line[0] != '[') {
            throw IllegalArgumentException("Invalid packet list: $line")
        }

        val lPkt = ListPacket()
        var remainder = line.substring(1)

        while (remainder.isNotEmpty()) {
            if (remainder[0] == '[') {
                val (pkt, rem) = parsePacketList(remainder)
                lPkt.add(pkt)
                remainder = rem
            } else if (remainder[0] == ']') {
                remainder = remainder.substring(1)
                return Pair(lPkt, remainder)
            } else if (remainder[0] == ',') {
                remainder = remainder.substring(1)
            } else {
                val (pkt, rem) = parseIntPacket(remainder)
                lPkt.add(pkt)
                remainder = rem
            }
        }

        throw IllegalArgumentException("line ended before reaching ]: $line")
    }

    private fun parseIntPacket(line: String): Pair<IntPacket, String> {
        if (!line[0].isDigit()) {
            throw IllegalArgumentException("Invalid int packet: $line")
        }

        val end = line.indexOfFirst { it == ',' || it == ']' }
        if (end == -1) {
            return Pair(IntPacket(line.toInt()), "")
        }

        return Pair(IntPacket(line.substring(0, end).toInt()), line.substring(end))
    }

    private fun part1(packetPairs: List<Pair<Packet, Packet>>): Int {
        var sum = 0
        for ((index, packetPair) in packetPairs.withIndex()) {
            if (log) {
                println("== Pair ${index+1} ==")
            }
            if (packetPairInOrder(packetPair)) {
                sum += (index + 1)
            }
        }
        return sum
    }

    private fun packetPairInOrder(pktPair: Pair<Packet, Packet>): Boolean {
        if (log) {println(" - Compare: ${pktPair.first} vs ${pktPair.second}")}
        if (pktPair.first !is ListPacket || pktPair.second !is ListPacket) {
            throw IllegalArgumentException("Invalid packet pair: $pktPair")
        }
        val pktPairFirst = pktPair.first.clone() as ListPacket
        val pktPairSecond = pktPair.second.clone() as ListPacket

        while (pktPairFirst.list.isNotEmpty() && pktPairSecond.list.isNotEmpty()) {
            val pkt1 = pktPairFirst.pop()
            val pkt2 = pktPairSecond.pop()
            when (compare(pkt1, pkt2)) {
                0 -> continue
                -1 -> return true
                1 -> return false
            }
        }
        return if (pktPairFirst.list.isEmpty()) {
            if (log) {println(" - First packet ran out - In order!")}
            true
        } else {
            if (log) {println(" - Second packet ran out - Out of order!")}
            false
        }
    }

    private fun compare(pkt1: Packet?, pkt2: Packet?): Int {
        if (log) {
            println("  - Compare: $pkt1 vs $pkt2")
        }
        if (pkt1 == null && pkt2 == null) {
            if (log) { println("    Equal") }
            return 0
        } else if (pkt1 != null && pkt2 == null) {
            if (log) { println("    Right is smaller - Out of Order") }
            return 1
        } else if (pkt1 == null /*&& pkt2 != null */) {
            if (log) { println("    Left is smaller - In order!") }
            return -1
        }
        val pkt1Clone = pkt1!!.clone()
        val pkt2Clone = pkt2!!.clone()

        if (pkt1Clone is IntPacket && pkt2Clone is IntPacket) {
            when(pkt1Clone.value.compareTo(pkt2Clone.value)) {
                0 -> {
                    if (log) { println("    Equal") }
                    return 0
                }
                -1 -> {
                    if (log) { println("    Left is smaller - In order!") }
                    return -1
                }
                1 -> {
                    if (log) { println("    Right is smaller - Out of Order") }
                    return 1
                }
            }
            throw Exception("Shouldn't get here")
        } else if (pkt1Clone is ListPacket && pkt2Clone is ListPacket) {
            while (pkt1Clone.list.isNotEmpty() && pkt2Clone.list.isNotEmpty()) {
                val pkt1Pop = pkt1Clone.pop()
                val pkt2Pop = pkt2Clone.pop()
                when (compare(pkt1Pop, pkt2Pop)) {
                    0 -> continue
                    -1 -> return -1
                    1 -> return 1
                }
            }
            return if (pkt1Clone.list.isEmpty() && pkt2Clone.list.isNotEmpty()) {
                if(log) { println("    Left side ran out of items, in order!") }
                -1
            } else if (pkt1Clone.list.isNotEmpty() /*&& pkt2Clone.list.isEmpty()*/) {
                if(log) { println("    Right side ran out of items, out of order!") }
                1
            } else {
                if(log) { println("    Both sides ran out of items, equal!") }
                0
            }
        } else if (pkt1Clone is IntPacket && pkt2Clone is ListPacket) {
            if(log) { println("    Mixed types, convert and check") }
            return compare(pkt1Clone.toList(), pkt2Clone)
        } else if (pkt1Clone is ListPacket && pkt2Clone is IntPacket) {
            if(log) { println("    Mixed types, convert and check") }
            return compare(pkt1Clone, pkt2Clone.toList())
        } else {
            throw IllegalArgumentException("Invalid packet pair: $pkt1Clone, $pkt2Clone")
        }
    }

    private fun part2(packetPairs: List<Pair<Packet, Packet>>): Int {
        val d2 = ListPacket(listOf(ListPacket(listOf(IntPacket(2)))))
        val d6 = ListPacket(listOf(ListPacket(listOf(IntPacket(6)))))

        val allPackets = packetPairs.flatMap { listOf(it.first, it.second) }.toMutableList()
        allPackets.addAll(listOf(d2, d6))

        val sorted = allPackets.sortedWith { o1, o2 ->
            compare(o1, o2)
        }

        return (sorted.indexOf(d2) + 1) * (sorted.indexOf(d6) + 1)
    }
}