import kotlin.time.measureTimedValue

typealias Input8 = List<String>
typealias Result8 = Int

fun main() {
    fun List<String>.parse(): Input8 {
        TODO()
    }

    fun Input8.part1(): Result8 {
        TODO()
    }

    fun Input8.part2(): Result8 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day8_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day8_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day8")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
