import kotlin.time.measureTimedValue

typealias Day10Data = List<String>
typealias Day10Result = Int

fun main() {
    fun List<String>.parse(): Day10Data {
        TODO()
    }

    fun Day10Data.part1(): Day10Result {
        TODO()
    }

    fun Day10Data.part2(): Day10Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day10_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day10_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day10")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
