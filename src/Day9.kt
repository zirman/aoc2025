import kotlin.time.measureTimedValue

typealias Day9Data = List<String>
typealias Day9Result = Int

fun main() {
    fun List<String>.parse(): Day9Data {
        TODO()
    }

    fun Day9Data.part1(): Day9Result {
        TODO()
    }

    fun Day9Data.part2(): Day9Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day9_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day9_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day9")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
