package day7

class FileNode(name: String, parent: Node, private val size: Int) : Node(name, parent) {
    override fun size(): Int {
        return size
    }
}