import kotlin.time.measureTimedValue

typealias Input11 = List<String>
typealias Result11 = Int

fun main() {
    fun List<String>.parse(): Input11 {
        TODO()
    }

    fun Input11.part1(): Result11 {
        TODO()
    }

    fun Input11.part2(): Result11 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day11_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day11_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day11")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
