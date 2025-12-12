import kotlin.time.measureTimedValue

typealias Input11 = Map<String, Set<String>>
typealias Result11 = Long

fun main() {
    fun List<String>.parse(): Input11 {
        return associate { line ->
            val (from, to) = line.split(": ")
            from to to.split(" ").toSet()
        }
    }

    fun Input11.part1(): Result11 {
        val memo = mutableMapOf<String, Long>()
        fun recur(from: String): Long = memo.getOrPut(from) {
            return@getOrPut if (from == "out") 1 else getValue(from).fold(0L) { acc, x -> acc + recur(x) }
        }
        return recur("you")
    }

    fun Input11.part2(): Result11 {
        val memo = mutableMapOf<Triple<String, Boolean, Boolean>, Long>()
        fun recur(prev: List<String>, from: String, dac: Boolean, fft: Boolean): Long =
            memo.getOrPut(Triple(from, dac, fft)) {
                return@getOrPut if (from == "out") {
                    if (dac && fft) 1 else 0
                } else {
                    val prev = prev + from
                    val dac = dac || from == "dac"
                    val fft = fft || from == "fft"
                    getValue(from).fold(0L) { acc, next ->
                        acc + recur(
                            prev = prev,
                            from = next,
                            dac = dac,
                            fft = fft,
                        )
                    }
                }
            }
        return recur(prev = emptyList(), from = "svr", dac = false, fft = false)
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day11_1_test")
    check(testInput1.parse().part1() == 5L)
    val testInput2 = readInput("Day11_2_test")
    check(testInput2.parse().part2().also { println("$it") } == 2L)
    val input = readInput("Day11")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
