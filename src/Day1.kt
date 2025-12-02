typealias Day1Data = List<Pair<Char, Int>>
typealias Day1Result = Int

fun main() {
    fun List<String>.parse(): Day1Data {
        return map { line -> Pair(line[0], line.drop(1).toInt()) }
    }

    fun Day1Data.part1(): Day1Result {
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

    fun Day1Data.part2(): Day1Result {
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
//    val testInput2 = readInput("Day1_2_test")
    check(testInput1.parse().part2() == 6)
    val input = readInput("Day1")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
    printlnMeasureTimeMillis {
        input.parse().part2().println()
    }
}
