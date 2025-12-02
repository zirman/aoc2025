typealias Day12Data = List<String>
typealias Day12Result = Int

fun main() {
    fun List<String>.parse(): Day12Data {
        TODO()
    }

    fun Day12Data.part1(): Day12Result {
        TODO()
    }

    fun Day12Data.part2(): Day12Result {
        TODO()
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day12_1_test")
    check(testInput1.parse().part1() == TODO())

//    val testInput2 = readInput("Day12_2_test")
//    check(testInput2.parse().part2() == TODO())

    val input = readInput("Day12")
    printlnMeasureTimeMillis {
        input.parse().part1().println()
    }
//    printlnMeasureTimeMillis {
//        input.parse().part2().println()
//    }
}
