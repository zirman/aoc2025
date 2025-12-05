import kotlin.time.measureTimedValue

typealias Day5Data = List<String>
typealias Day5Result = Int

fun main() {
    fun List<String>.parse(): Day5Data {
        TODO()
    }

    fun Day5Data.part1(): Day5Result {
        TODO()
    }

    fun Day5Data.part2(): Day5Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day5_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day5_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day5")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
