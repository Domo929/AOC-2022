package day07

class DirNode(name: String, parent: Node?) : Node(name, parent) {
    override fun size(): Int {
        var sum = 0
        for (child in children) {
            sum += child.size()
        }
        return sum
    }
}