import kotlin.math.max
import kotlin.time.measureTimedValue

private data class Input12(val shapes: List<Stack>, val regions: List<Region>)
private typealias Result12 = Long

private data class Region(val width: Int, val length: Int, val presents: List<Int>)

private val shapeIndexRegex = """^(\d+):$""".toRegex()
private val presentsRegex = """^(\d+)x(\d+): (\d+) (\d+) (\d+) (\d+) (\d+) (\d+)$""".toRegex()

private data class Memo12(
    val presents: Stack,
    val width: Int,
    val length: Int,
    val a: Int,
    val b: Int,
    val c: Int,
    val d: Int,
    val e: Int,
    val f: Int,
)

data class Stack(val boxes: Boxes, val width: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Stack

        if (width != other.width) return false
        if (!boxes.contentEquals(other.boxes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + boxes.contentHashCode()
        return result
    }

    override fun toString(): String = buildString {
        for (i in 0..<boxes.size) {
            for (k in 1..width) {
                if ((0b1L shl (Long.SIZE_BITS - k)) and boxes[i] != 0L) {
                    append('#')
                } else {
                    append('.')
                }
            }
            append('\n')
        }
    }
}

typealias Boxes = LongArray

fun main() {
    fun List<String>.parse(): Input12 {
        val groups = joinToString("\n") { it }.split("\n\n")
        val shapesStrings = groups.dropLast(1)
        val regionsString = groups.last()
        return Input12(
            shapes = shapesStrings.mapIndexed { i, group ->
                val lines = group.split("\n")
                val (index) = checkNotNull(shapeIndexRegex.matchEntire(lines.first())).destructured
                assert(index.toInt() == i)
                val bitsString = lines.drop(1)
                Stack(
                    boxes = Boxes(bitsString.size) { i ->
                        var lineBits = 0L
                        bitsString[i].forEachIndexed { k, c ->
                            when (c) {
                                '#' -> {
                                    lineBits = lineBits or (0b1L shl (Long.SIZE_BITS - k - 1))
                                }

                                '.' -> {}
                                else -> {
                                    error("invalid character")
                                }
                            }
                        }
                        lineBits
                    },
                    width = bitsString.maxOf { it.length },
                )
            },
            regions = regionsString.split("\n").map { line ->
                val (width, length, a, b, c, d, e, f) = checkNotNull(presentsRegex.matchEntire(line)).destructured
                Region(
                    width = width.toInt(),
                    length = length.toInt(),
                    presents = List(6) {
                        when (it) {
                            0 -> a
                            1 -> b
                            2 -> c
                            3 -> d
                            4 -> e
                            5 -> f
                            else -> error("")
                        }.toInt()
                    },
                )
            },
        )
    }

    fun Stack.rotate(): Stack {
        return Stack(
            boxes = Boxes(width) { col ->
                var acc = 0L
                val mask = 0b1L shl (Long.SIZE_BITS - (width - col))
                for (i in 0..<boxes.size) {
                    if (boxes[i] and mask != 0L) {
                        acc = acc or (0b1L shl (Long.SIZE_BITS - i - 1))
                    }
                }
                acc
            },
            width = boxes.size,
        )
    }

    fun Stack.flipX(): Stack {
        return Stack(
            boxes = Boxes(boxes.size) { col ->
                var acc = 0L
                for (i in 1..width) {
                    acc = acc or (boxes[col] and (0b1L shl (Long.SIZE_BITS - i)))
                }
                acc
            },
            width = width,
        )
    }

    fun Stack.flipY(): Stack {
        return Stack(
            boxes = Boxes(boxes.size) { col ->
                boxes[boxes.size - col - 1]
            },
            width = width,
        )
    }

    fun Stack.shift(x: Int, y: Int): Stack {
        assert(x in 0..Long.SIZE_BITS - width)
        assert(y >= 0)
        return Stack(
            boxes = Boxes(boxes.size + y) { row ->
                if (row - y >= 0 && row - y < boxes.size) {
                    boxes[row - y] ushr x
                } else {
                    0L
                }
            },
            width = width + x,
        )
    }

    fun Stack.combine(shape: Stack): Stack? {
        val newWidth = max(width, shape.width)
        return Stack(
            boxes = Boxes(max(boxes.size, shape.boxes.size)) { row ->

                val lhs = if (row < boxes.size) boxes[row] else 0L
                val rhs = if (row < shape.boxes.size) shape.boxes[row] else 0L
                val acc = lhs xor rhs
                if (lhs.countOneBits() + rhs.countOneBits() != acc.countOneBits()) {
                    return@combine null
                }
                acc
            },
            width = newWidth,
        )
    }

    fun Stack.countBoxes(): Int {
        return boxes.sumOf { it.countOneBits() }
    }

    fun Input12.part1(): Result12 {
        assert(regions.maxOf { it.width } <= Long.SIZE_BITS)
        val rotated = buildMap {
            for (i in shapes.indices) {
                val shape = shapes[i]
                this[i] = buildList {
                    add(shape)
                    add(last().rotate())
                    add(last().rotate())
                    add(last().rotate())
                    add(shape.flipX())
                    add(last().rotate())
                    add(last().rotate())
                    add(last().rotate())
                    add(shape.flipY())
                    add(last().rotate())
                    add(last().rotate())
                    add(last().rotate())
                }.toSet()
            }
        }
        val memo = mutableMapOf<Memo12, Boolean>()
        fun recur(
            presents: Stack,
            width: Int,
            length: Int,
            a: Int,
            b: Int,
            c: Int,
            d: Int,
            e: Int,
            f: Int,
        ): Boolean = memo.getOrPut(Memo12(presents, width, length, a, b, c, d, e, f)) {
            if (a == 0 && b == 0 && c == 0 && d == 0 && e == 0 && f == 0) {
                return@getOrPut true
            }
            if (a > 0) {
                rotated.getValue(0).forEach { shape ->
                    for (x in 0..width - shape.width) {
                        for (y in 0..length - shape.boxes.size) {
                            val s = shape.shift(x, y)
                            val p = presents.combine(s)
                            if (p != null) {
                                if (recur(p, width, length, a - 1, b, c, d, e, f)) {
                                    return@getOrPut true
                                }
                            }
                        }
                    }
                }
            }
            if (b > 0) {
                rotated.getValue(1).forEach { shape ->
                    for (x in 0..width - shape.width) {
                        for (y in 0..length - shape.boxes.size) {
                            val s = shape.shift(x, y)
                            val p = presents.combine(s)
                            if (p != null) {
                                if (recur(p, width, length, a, b - 1, c, d, e, f)) {
                                    return@getOrPut true
                                }
                            }
                        }
                    }
                }
            }
            if (c > 0) {
                rotated.getValue(2).forEach { shape ->
                    for (x in 0..width - shape.width) {
                        for (y in 0..length - shape.boxes.size) {
                            val s = shape.shift(x, y)
                            val p = presents.combine(s)
                            if (p != null) {
                                if (recur(p, width, length, a, b, c - 1, d, e, f)) {
                                    return@getOrPut true
                                }
                            }
                        }
                    }
                }
            }
            if (d > 0) {
                rotated.getValue(3).forEach { shape ->
                    for (x in 0..width - shape.width) {
                        for (y in 0..length - shape.boxes.size) {
                            val s = shape.shift(x, y)
                            val p = presents.combine(s)
                            if (p != null) {
                                if (recur(p, width, length, a, b, c, d - 1, e, f)) {
                                    return@getOrPut true
                                }
                            }
                        }
                    }
                }
            }
            if (e > 0) {
                rotated.getValue(4).forEach { shape ->
                    for (x in 0..width - shape.width) {
                        for (y in 0..length - shape.boxes.size) {
                            val s = shape.shift(x, y)
                            val p = presents.combine(s)
                            if (p != null) {
                                if (recur(p, width, length, a, b, c, d, e - 1, f)) {
                                    return@getOrPut true
                                }
                            }
                        }
                    }
                }
            }
            if (f > 0) {
                rotated.getValue(5).forEach { shape ->
                    for (x in 0..width - shape.width) {
                        for (y in 0..length - shape.boxes.size) {
                            val s = shape.shift(x, y)
                            val p = presents.combine(s)
                            if (p != null) {
                                if (recur(p, width, length, a, b, c, d, e, f - 1)) {
                                    return@getOrPut true
                                }
                            }
                        }
                    }
                }
            }
            return@getOrPut false
        }
        return regions.fold(0L) { acc, region ->
            if (
                region.presents
                    .mapIndexed { i, count -> count * shapes[i].countBoxes() }
                    .sum() > region.width * region.length
            ) {
                acc
            } else if (
                recur(
                    presents = Stack(Boxes(0) { 0L }, 0),
                    width = region.width,
                    length = region.length,
                    a = region.presents[0],
                    b = region.presents[1],
                    c = region.presents[2],
                    d = region.presents[3],
                    e = region.presents[4],
                    f = region.presents[5],
                )
            ) {
                acc + 1L
            } else {
                acc
            }
        }
    }
    // test if implementation meets criteria from the description, like:
    val testInput1 = readInput("Day12_1_test")
    measureTimedValue { check(testInput1.parse().part1() == 2L) }.println()
    val input = readInput("Day12")
    measureTimedValue { input.parse().part1() }.println()
}
