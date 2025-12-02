typealias Day8Data = List<String>
typealias Day8Result = Int

fun main() {
    fun List<String>.parse(): Day8Data {
        TODO()
    }

    fun Day8Data.part1(): Day8Result {
        TODO()
    }

    fun Day8Data.part2(): Day8Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day8_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day8_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day8")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
//    printlnMeasureTimeMillis {
//        input.parse().part2().println()
//    }
}
