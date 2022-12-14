package day13

class IntPacket(val value: Int):Packet() {

    fun toList(): ListPacket {
        val list = ListPacket()
        list.add(this)
        return list
    }

    override fun clone(): IntPacket {
        return IntPacket(value)
    }

    override fun toString(): String {
        return value.toString()
    }
}