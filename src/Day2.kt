typealias Day2Data = List<String>
typealias Day2Result = Int

fun main() {
    fun List<String>.parse(): Day2Data {
        TODO()
    }

    fun Day2Data.part1(): Day2Result {
        TODO()
    }

    fun Day2Data.part2(): Day2Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day2_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day2_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day2")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
//    printlnMeasureTimeMillis {
//        input.parse().part2().println()
//    }
}
