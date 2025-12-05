import kotlin.time.measureTimedValue

typealias Input7 = List<String>
typealias Result7 = Int

fun main() {
    fun List<String>.parse(): Input7 {
        TODO()
    }

    fun Input7.part1(): Result7 {
        TODO()
    }

    fun Input7.part2(): Result7 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day7_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day7_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day7")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
