import kotlin.time.measureTimedValue

typealias Input12 = List<String>
typealias Result12 = Int

fun main() {
    fun List<String>.parse(): Input12 {
        TODO()
    }

    fun Input12.part1(): Result12 {
        TODO()
    }

    fun Input12.part2(): Result12 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day12_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day12_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day12")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
