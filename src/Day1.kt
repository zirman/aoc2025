import kotlin.time.measureTimedValue

private typealias Input1 = List<Pair<Char, Int>>
private typealias Result1 = Int

fun main() {
    fun List<String>.parse(): Input1 {
        return map { line -> Pair(line[0], line.drop(1).toInt()) }
    }

    fun Input1.part1(): Result1 {
        var dial = 50
        var count = 0
        forEach { (dir, rot) ->
            dial = when (dir) {
                'L' -> (dial - rot).mod(100)
                'R' -> (dial + rot).mod(100)
                else -> error("Invalid direction")
            }
            if (dial == 0) {
                count++
            }
        }
        return count
    }

    fun Input1.part2(): Result1 {
        var dial = 50
        var count = 0
        forEach { (dir, rot) ->
            dial = when (dir) {
                'L' -> {
                    val passed = rot / 100
                    count += passed
                    count += (if (dial > 0 && (rot - (passed * 100)) >= dial) 1 else 0)
                    (dial - rot).mod(100)
                }

                'R' -> {
                    val passed = rot / 100
                    count += passed
                    count += (if (dial > 0 && dial + (rot - (passed * 100)) >= 100) 1 else 0)
                    (dial + rot).mod(100)
                }

                else -> {
                    error("Invalid direction")
                }
            }
        }
        return count
    }
    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day1_1_test")
    check(testInput1.parse().part1() == 3)
    check(testInput1.parse().part2() == 6)
    val input = readInput("Day1")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
