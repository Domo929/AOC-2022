package shared

class Grid<T>(width: Int, height: Int, private val defaultValue: T, private val rock: T) {
    private val grid: MutableList<MutableList<T>> = mutableListOf()

    init {
        for (y in 0 until height) {
            val row = mutableListOf<T>()
            for (x in 0 until width) {
                row.add(defaultValue)
            }
            grid.add(row)
        }
    }

    fun add(t: T, x: Int, y: Int) {
        for(i in grid.size until y+1 ) {
            grid.add(mutableListOf())
        }
        for(i in grid[y].size until x+1) {
            grid[y].add(defaultValue)
        }
        grid[y][x] = t
    }

    fun addFloor(y: Int){
        for(i in grid.size until y+1 ) {
            grid.add(mutableListOf())
        }
        for(x in 0 until grid[0].size) {
            grid[y][x] = rock
        }
    }

    fun at(x: Int, y: Int): T {
        return try {
            grid[y][x]
        } catch (e: IndexOutOfBoundsException) {
            this.add(defaultValue, x, y)
            grid[y][x]
        }
    }

    fun at(coordinate: Pair<Int, Int>): T{
        return at(coordinate.first, coordinate.second)
    }

    fun isEmpty(coordinate: Pair<Int, Int>): Boolean {
        return at(coordinate) == defaultValue
    }

    fun print() {
        grid.forEach { row ->
            row.forEach { print(it) }
            println()
        }
    }

    fun isFreefall(x: Int, y:Int): Boolean {
        for(i in y until grid.size) {
            if(this.at(x, i) != defaultValue) {
                return false
            }
        }
        return true
    }

    fun isFreefall(coordinate: Pair<Int, Int>): Boolean {
        return isFreefall(coordinate.first, coordinate.second)
    }

    fun findDeepestY(): Int {
        for(y in grid.size-1 downTo 0) {
            for(x in 0 until grid[y].size) {
                if(grid[y][x] != defaultValue) {
                    return y
                }
            }
        }
        return 0
    }
}