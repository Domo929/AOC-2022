import java.io.File

abstract class Day(path: String) {
    val lines: List<String> = File(path).readLines()

    abstract fun solve(): Pair<Any, Any>
}