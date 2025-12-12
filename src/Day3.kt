import kotlin.time.measureTimedValue

private typealias Input3 = List<List<Int>>
private typealias Result3 = Long

fun main() {
    fun List<String>.parse(): Input3 {
        return map { it.map { it.digitToInt() } }
    }

    fun Input3.part1(batteries: Int): Result3 {
        return sumOf { bank ->
            var joltage = 0L
            var batteries = batteries
            var index = 0
            while (batteries > 0) {
                val bank1 = bank.subList(index, bank.size - (batteries - 1))
                val max = bank1.max()
                joltage *= 10
                joltage += max
                index += bank1.indexOf(max) + 1
                batteries--
            }
            joltage
        }
    }
    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day3_1_test")
    check(testInput1.parse().part1(2) == 357L)
    check(testInput1.parse().part1(12) == 3121910778619L)
    val input = readInput("Day3")
    measureTimedValue { input.parse().part1(2) }.println()
    measureTimedValue { input.parse().part1(12) }.println()
}
