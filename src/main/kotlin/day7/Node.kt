package day7

abstract class Node(val name: String, val parent: Node?) {
    protected val children = mutableListOf<Node>()

    abstract fun size(): Int

    fun add(node: Node) {
        children.add(node)
    }

    fun cd(name: String): Node? {
        when (name) {
            ".." -> return parent
            "." -> return this
            "/" -> {
                var current = this
                while (current.parent != null) {
                    current = current.parent!!
                }
                return current
            }

            else -> {
                for (child in children) {
                    if (child.name == name) {
                        return child
                    }
                }
            }
        }

        return null
    }

    fun getDirectories(): List<Node> {
        val dirs = mutableListOf<Node>()
        if (this is DirNode) {
            dirs.add(this)
        }
        for (child in children) {
            dirs.addAll(child.getDirectories())
        }
        return dirs
    }
}