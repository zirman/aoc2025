typealias Day4Data = List<String>
typealias Day4Result = Int

fun main() {
    fun List<String>.parse(): Day4Data {
        TODO()
    }

    fun Day4Data.part1(): Day4Result {
        TODO()
    }

    fun Day4Data.part2(): Day4Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day4_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day4_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day4")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
//    printlnMeasureTimeMillis {
//        input.parse().part2().println()
//    }
}
