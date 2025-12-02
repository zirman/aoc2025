import kotlin.time.measureTimedValue

typealias Input3 = List<String>
typealias Result3 = Int

fun main() {
    fun List<String>.parse(): Input3 {
        TODO()
    }

    fun Input3.part1(): Result3 {
        TODO()
    }

    fun Input3.part2(): Result3 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day3_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day3_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day3")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.print()
}
