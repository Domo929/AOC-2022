package day13

class ListPacket(): Packet() {
    val list = ArrayDeque<Packet>()

    constructor(packetList: List<Packet>) : this() {
        list.addAll(packetList)
    }
    fun add(pkt: Packet) {
        list.addLast(pkt)
    }

    fun pop(): Packet? {
        return try {
            list.removeFirst()
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun clone(): ListPacket {
        val newList = ArrayDeque<Packet>()
        for (pkt in list) {
            newList.addLast(pkt.clone())
        }
        return ListPacket(newList)
    }

    override fun toString(): String {
        return list.toString()
    }
}