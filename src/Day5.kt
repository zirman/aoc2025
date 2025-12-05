import kotlin.time.measureTimedValue

typealias Input5 = Pair<List<LongRange>, List<Long>>
typealias Result5 = Long

fun main() {
    fun List<String>.parse(): Input5 {
        val (fresh, avail) = joinToString("\n") { it }.split("\n\n")
        return Pair(
            fresh.split("\n").map {
                val (a, b) = it.split("-")
                a.toLong()..b.toLong()
            },
            avail.split("\n").map { it.toLong() },
        )
    }

    fun Input5.part1(): Result5 {
        val (fresh, avail) = this
        var count = 0L
        avail.forEach { if (fresh.any { range -> it in range }) count++ }
        return count
    }

    fun List<LongRange>.recur(r1: LongRange): List<LongRange> {
        if (r1.isEmpty()) {
            return emptyList()
        }
        if (isEmpty()) {
            return listOf(r1)
        }
        val r2 = first()
        return if (r1.first < r2.first && r1.last > r2.last) {
            drop(1).recur(r1.first..<r2.first) +
                drop(1).recur(r2.last + 1..r1.last)
        } else if (r1.first in r2) {
            if (r1.last in r2) {
                emptyList()
            } else {
                drop(1).recur(r2.last + 1..r1.last)
            }
        } else if (r1.last in r2) {
            drop(1).recur(r1.first..<r2.first)
        } else {
            drop(1).recur(r1)
        }
    }

    fun Input5.part2(): Result5 {
        var ranges = emptyList<LongRange>()
        val (fresh) = this
        fresh.forEach { r ->
            ranges = ranges + ranges.recur(r)
        }
        var count = 0L
        ranges.forEach {
            count += (it.last - it.first) + 1
        }
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day5_1_test")
    check(testInput1.parse().part1() == 3L)
    check(testInput1.parse().part2() == 14L)
    val input = readInput("Day5")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
