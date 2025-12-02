import kotlin.time.measureTimedValue

typealias Day7Data = List<String>
typealias Day7Result = Int

fun main() {
    fun List<String>.parse(): Day7Data {
        TODO()
    }

    fun Day7Data.part1(): Day7Result {
        TODO()
    }

    fun Day7Data.part2(): Day7Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day7_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day7_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day7")
    measureTimedValue { input.parse().part1() }.println()
//    measureTimedValue { input.parse().part2() }.print()
}
