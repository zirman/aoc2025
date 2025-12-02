typealias Day11Data = List<String>
typealias Day11Result = Int

fun main() {
    fun List<String>.parse(): Day11Data {
        TODO()
    }

    fun Day11Data.part1(): Day11Result {
        TODO()
    }

    fun Day11Data.part2(): Day11Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day11_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day11_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day11")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
//    printlnMeasureTimeMillis {
//        input.parse().part2().println()
//    }
}
