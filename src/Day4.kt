import kotlin.time.measureTimedValue

typealias Input4 = List<String>
typealias Result4 = Int

fun main() {
    fun List<String>.parse(): Input4 {
        TODO()
    }

    fun Input4.part1(): Result4 {
        TODO()
    }

    fun Input4.part2(): Result4 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day4_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day4_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day4")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.print()
}
