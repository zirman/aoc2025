import kotlin.time.measureTimedValue

typealias Input9 = List<String>
typealias Result9 = Int

fun main() {
    fun List<String>.parse(): Input9 {
        TODO()
    }

    fun Input9.part1(): Result9 {
        TODO()
    }

    fun Input9.part2(): Result9 {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day9_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day9_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day9")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.println()
}
