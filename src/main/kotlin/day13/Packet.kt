package day13

abstract class Packet: Cloneable {
    public abstract override fun clone(): Packet
}