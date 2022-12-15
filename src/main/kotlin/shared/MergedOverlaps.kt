package shared

import java.util.*

// Taken from https://www.geeksforgeeks.org/merging-intervals/
class MergeOverlappingIntervals {
    // The main function that takes a set of intervals, merges
    // overlapping intervals and prints the result
    fun mergeIntervals(unmerged: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        // Test if the given set has at least one interval
        if (unmerged.isEmpty()) {
            return unmerged
        }

        // Create an empty stack of intervals
        val stack = Stack<Pair<Int, Int>>()

        // sort the intervals in increasing order of start time
        val sorted = unmerged.sortedBy { it.first }

        // push the first interval to stack
        stack.push(sorted[0])

        // Start from the next interval and merge if necessary
        for (i in 1 until sorted.size) {
            // get interval from stack top
            val top = stack.peek()

            // if current interval is not overlapping with stack top,
            // push it to the stack
            if (top.second < sorted[i].first) {
                stack.push(sorted[i])
            } else if (top.second < sorted[i].second) {
                val newTop = Pair(top.first, sorted[i].second)
                stack.pop()
                stack.push(newTop)
            }
        }

        return stack.toList()
    }
}