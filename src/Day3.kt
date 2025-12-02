typealias Day3Data = List<String>
typealias Day3Result = Int

fun main() {
    fun List<String>.parse(): Day3Data {
        TODO()
    }

    fun Day3Data.part1(): Day3Result {
        TODO()
    }

    fun Day3Data.part2(): Day3Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day3_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day3_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day3")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
//    printlnMeasureTimeMillis {
//        input.parse().part2().println()
//    }
}
