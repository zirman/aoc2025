import kotlin.time.measureTimedValue

private typealias Input4 = List<List<Char>>
private typealias Result4 = Int

fun main() {
    fun List<String>.parse(): Input4 {
        return map { it.toList() }
    }

    fun Input4.part1(): Result4 {
        val size = toSize2()
        var count = 0
        for (r in indices) {
            for (c in this[r].indices) {
                val pos = Pos(r, c)
                if (this[pos] == '@' &&
                    pos.nextWithDiag(size).count { pos -> this[pos] == '@' } < 4
                ) {
                    count++
                }
            }
        }
        return count
    }

    fun Input4.part2(): Result4 {
        val size = toSize2()
        var count = 0
        var foo = this
        while (true) {
            var x = 0
            foo = foo.mapIndexed { row, line ->
                line.mapIndexed { col, tp ->
                    val pos = Pos(row, col)
                    if (tp == '@') {
                        if (pos.nextWithDiag(size).count { pos -> foo[pos] == '@' } < 4) {
                            x++
                            '.'
                        } else {
                            '@'
                        }
                    } else {
                        '.'
                    }
                }
            }
            if (x == 0) break
            count += x
        }
        return count
    }
    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day4_1_test")
    check(testInput1.parse().part1() == 13)
    check(testInput1.parse().part2().also { println(it) } == 43)
    val input = readInput("Day4")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
