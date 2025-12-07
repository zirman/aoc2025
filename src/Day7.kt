import kotlin.time.measureTimedValue
import plus

typealias Input7 = List<List<Char>>
typealias Result7 = Long

fun main() {
    fun List<String>.parse(): Input7 {
        return map { it.toList() }
    }

    fun Input7.part1(): Result7 {
        var beams = setOf(first().indexOf('S'))
        val g = map { it.toMutableList() }
        var splitCount = 0L
        for (line in g.drop(1)) {
            beams = beams.flatMap { col ->
                if (line[col] == '^') {
                    splitCount++
                    listOf(col - 1, col + 1)
                } else {
                    listOf(col)
                }
            }.toSet()
            beams.forEach { col ->
                line[col] = '|'
            }
        }
        return splitCount
    }

    fun Input7.part2(): Result7 {
        var beams = mapOf(first().indexOf('S') to 1L)
        val g = map { it.toMutableList() }
        for (line in g.drop(1)) {
            beams = buildMap {
                beams.forEach { (col, count) ->
                    if (line[col] == '^') {
                        set(col - 1, getOrDefault(col - 1, 0) + count)
                        set(col + 1, getOrDefault(col + 1, 0) + count)
                    } else {
                        set(col, getOrDefault(col, 0) + count)
                    }
                }
            }
            beams.keys.forEach { col ->
                line[col] = '|'
            }
        }
        return beams.values.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day7_1_test")
    check(testInput1.parse().part1() == 21L)
    check(testInput1.parse().part2() == 40L)
    val input = readInput("Day7")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
