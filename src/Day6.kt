import kotlin.time.measureTimedValue

private typealias Input6 = List<List<String>>
private typealias Result6 = Long

fun main() {
    fun List<String>.parse(): Input6 = map { it.trim().split(spaceRegex) }

    fun Input6.part1(): Result6 {
        return last()
            .mapIndexed { col, op ->
                when (op) {
                    "*" -> dropLast(1)
                        .map { it[col] }
                        .fold(1L) { a, b -> a * b.toInt() }

                    "+" -> dropLast(1).map { it[col] }.fold(0L) { a, b -> a + b.toInt() }
                    else -> error("Invalid operation $op")
                }
            }
            .sum()
    }

    fun List<List<Char>>.part2(): Result6 {
        val digits = dropLast(1)
        val ops = last().mapIndexedNotNull { i, c ->
            if (c == ' ') {
                null
            } else {
                Pair(
                    c,
                    (i..<digits.maxOf { it.size }).first { k ->
                        digits.all { line -> (line.drop(k + 1).firstOrNull() ?: ' ').isWhitespace() }
                    } downTo i,
                )
            }
        }
        return ops.fold(0L) { accumulator, (op, range) ->
            val numbers = range.map { col ->
                digits.joinToString("") { line -> line.getOrNull(col)?.takeIf { it.isDigit() }?.toString() ?: "" }
                    .toLong()
            }
            accumulator + when (op) {
                '+' -> {
                    numbers.fold(0L) { a, b -> a + b }
                }

                '*' -> {
                    numbers.fold(1L) { a, b -> a * b }
                }

                else -> error("Invalid operation $op")
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day6_1_test")
    check(testInput1.parse().part1() == 4277556L)
    check(testInput1.map { it.toList() }.part2() == 3263827L)

    val input = readInput("Day6")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.map { it.toList() }.part2() }.println()
}
