import kotlin.math.min
import kotlin.time.measureTimedValue

private typealias Input10 = List<Day10>
private typealias Result10 = Long

private data class Day10(val indicatorLights: Int, val toggleButtons: List<Int>, val joltages: List<Int>) {
    override fun toString(): String {
        return "[${
            buildString {
                var i = indicatorLights
                while (i != 0) {
                    append(
                        when (i and 0b1) {
                            0b1 -> '#'
                            0b0 -> '.'
                            else -> error("never")
                        },
                    )
                    i = i shr 1
                }
            }
        }] ${
            toggleButtons.joinToString(separator = " ") { toggleButton ->
                buildList {
                    var i = toggleButton
                    var c = 0
                    while (i != 0) {
                        when (i and 0b1) {
                            0b1 -> add(c)
                            0b0 -> {}
                            else -> error("never")
                        }
                        i = i shr 1
                        c++
                    }
                }.joinToString(separator = ",", prefix = "(", postfix = ")") { "$it" }
            }
        } ${
            joltages.joinToString(",", "{", "}") { "$it" }
        }"
    }
}

fun main() {
    fun List<String>.parse(): Input10 {
        return map { line ->
            val fields = line.split(" ")
            Day10(
                indicatorLights = fields.first()
                    .removePrefix("[").removeSuffix("]")
                    .reversed()
                    .fold(0b0) { acc, c ->
                        when (c) {
                            '#' -> (acc shl 1) or 0b1
                            '.' -> acc shl 1
                            else -> error("never")
                        }
                    },
                toggleButtons = fields.drop(1).dropLast(1).map { toggleButtonStrings ->
                    toggleButtonStrings.removePrefix("(").removeSuffix(")").split(",").fold(0b0) { acc, x ->
                        acc or (0b1 shl x.toInt())
                    }
                },
                joltages = fields.last().removePrefix("{").removeSuffix("}").split(",").map { s ->
                    s.toInt()
                },
            )
        }
    }

    fun Input10.part1(): Result10 {
        return fold(0L) { acc, day10 ->
            val (indicatorLights, toggleButtons) = day10
            val visited = mutableSetOf<Int>()
            tailrec fun recur(lights: Set<Int>, depth: Int): Int {
                if (indicatorLights in lights) return depth
                val x = lights.filter { it !in visited }
                visited.addAll(x)

                return recur(
                    x.flatMap { light ->
                        toggleButtons.map { button ->
                            (button.inv() and light) or ((button and light).inv() and button)
                        }
                    }
                        .toSet(),
                    depth + 1,
                )
            }
            acc + recur(setOf(element = 0b0), 0)
        }
    }

    fun Input10.part2(): Result10 {
        return fold(0L) { acc, day10 ->
            val (_, toggleButtons, joltages) = day10
//            println(day10)
            val sortedButtons: List<Pair<Int, Int>> =
                toggleButtons.mapIndexed { index, i -> Pair(i, index) }.sortedByDescending { (buttons) ->
                    buttons.countOneBits()
                }
//            println(sortedButtons)
            val equ = joltages.mapIndexed { i, joltage ->
                val mask = 0b1 shl i
                Pair(sortedButtons.filter { (i) -> i and mask == mask }.map { (_, index) -> index }, joltage)
            }
//            println(equ)
            val ranges = List(toggleButtons.size) { i ->
                val max = equ.minOf { (n, total) -> if (i in n) total else Int.MAX_VALUE }
                if (max == Int.MAX_VALUE) error("no dependencies")
                0..max
            }

            //            println(ranges)
            fun recur(buttons: List<Pair<Int, Int>>, joltages: List<Int>, steps: Long, best: Long): Long {
                if (joltages.all { it == 0 }) {
                    return steps
                }
                if (buttons.isEmpty()) {
                    return Long.MAX_VALUE
                }
                if (steps > best) {
                    return Long.MAX_VALUE
                }
                val (button, index) = buttons.first()
                return ranges[index].reversed().fold(Long.MAX_VALUE) { acc, count ->
                    min(
                        acc,
                        recur(
                            buttons = buttons.drop(1),
                            joltages = joltages.mapIndexed { index, joltage ->
                                if ((button and (0b1 shl index)) != 0) {
                                    // too much
                                    if (count > joltage) {
                                        return@fold Long.MAX_VALUE
                                    }
                                    joltage - count
                                } else {
                                    joltage
                                }
                            },
                            steps = steps + count,
                            best = best - (steps + count),
                        )
                    )
                }
            }
            acc + recur(buttons = sortedButtons, joltages = joltages, steps = 0L, best = Long.MAX_VALUE).also {
                println(it)
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day10_1_test")
    check(testInput1.parse().part1() == 7L)
    check(testInput1.parse().part2() == 33L)
    println("asdf")
    val input = readInput("Day10")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
