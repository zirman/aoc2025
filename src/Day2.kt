typealias Day2Data = List<List<Long>>
typealias Day2Result = Long

fun main() {
    fun Long.isInvalid1(): Boolean {
        val s = toString()
        return if (s.length % 2 == 1) {
            false
        } else {
            val mid = s.length / 2
            s.take(mid) == s.substring(mid)
        }
    }

    fun Long.isInvalid2(): Boolean {
        val s = toString()
        var w = 1
        while (w <= s.length / 2) {
            if (s.length % w == 0) {
                val u = s.windowed(size = w, step = w)
                if (u.all { it == u[0] }) {
                    return true
                }
            }
            w++
        }
        return false
    }

    fun List<String>.parse(): Day2Data {
        return flatMap { it.split(',') }
            .map { it.split('-').map { it.toLong() } }
    }

    fun Day2Data.part1(): Day2Result {
        var sum: Long = 0
        forEach { (start, end) ->
            sum += (start..end).sumOf { if (it.isInvalid1()) it else 0 }
        }
        return sum
    }

    fun Day2Data.part2(): Day2Result {
        var sum: Long = 0
        forEach { (start, end) ->
            sum += (start..end).sumOf { if (it.isInvalid2()) it else 0 }
        }
        return sum
    }
    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day2_1_test")
    check(testInput1.parse().part1() == 1227775554L)
    check(testInput1.parse().part2() == 4174379265L)
    val input = readInput("Day2")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
    printlnMeasureTimeMillis {
        input.parse().part2().println()
    }
}
