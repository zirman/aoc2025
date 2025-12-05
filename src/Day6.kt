import kotlin.time.measureTimedValue

typealias Input6 = List<String>
typealias Result6 = Int

fun main() {
    fun List<String>.parse(): Input6 {
        TODO()
    }

    fun Input6.part1(): Result6 {
        TODO()
    }

    fun Input6.part2(): Result6 {
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
