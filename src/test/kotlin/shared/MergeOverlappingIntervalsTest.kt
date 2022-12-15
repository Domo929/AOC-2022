package shared

import org.junit.jupiter.api.Assertions.*

class MergeOverlappingIntervalsTest {

    @org.junit.jupiter.api.Test
    fun mergeIntervals() {
        val intervals = listOf(Pair(12,14), Pair(6,10), Pair(0,12), Pair(14,20))
        val expected = listOf(Pair(0, 20))
        val actual = MergeOverlappingIntervals().mergeIntervals(intervals)
        assertEquals(expected, actual)
    }
}