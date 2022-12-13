package day07

class FileNode(name: String, parent: Node, private val size: Int) : Node(name, parent) {
    override fun size(): Int {
        return size
    }

    override fun add(node: Node) {
        throw Exception("Cannot add a child to a file")
    }
}