import kotlin.time.measureTimedValue

typealias Day6Data = List<String>
typealias Day6Result = Int

fun main() {
    fun List<String>.parse(): Day6Data {
        TODO()
    }

    fun Day6Data.part1(): Day6Result {
        TODO()
    }

    fun Day6Data.part2(): Day6Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day6_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day6_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day6")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
