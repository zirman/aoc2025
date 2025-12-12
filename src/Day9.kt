import kotlin.math.max
import kotlin.math.min
import kotlin.time.measureTimedValue

private typealias Input9 = List<Pos>
private typealias Result9 = Long

fun main() {
    fun List<String>.parse(): Input9 {
        return map { line ->
            val (col, row) = line.split(",")
                .map { it.toInt() }
            Pos(row, col)
        }
    }

    fun Input9.part1(): Result9 {
        var max = Long.MIN_VALUE
        for (i in indices) {
            for (k in i + 1..indices.last) {
                val rect = this[i] - this[k]
                val area = (rect.row.toLong() + 1) * (rect.col + 1)
                if (area > max) {
                    max = area
                }
            }
        }
        return max
    }

    fun Input9.part2(): Result9 {
        val corners = mapIndexed { index, pos -> Pair(index, pos) }.toMutableList()
        val areas = mutableListOf<Triple<Int, Int, Long>>()
        for (i in this@part2.indices) {
            for (k in i + 1..this@part2.indices.last) {
                val rect = this@part2[i] - this@part2[k]
                val area = (rect.row.toLong() + 1) * (rect.col + 1)
                areas.add(Triple(i, k, area))
            }
        }
        areas.sortByDescending { it.third }
        corners.sortBy { it.second.col }
        var shiftedCol = 0
        var i = 0
        while (i <= corners.indices.last) {
            val col = corners[i].second.col
            do {
                if (corners[i].second.col == col) {
                    corners[i] = corners[i].copy(second = corners[i].second.copy(col = shiftedCol))
                } else {
                    break
                }
                i++
            } while (i <= corners.indices.last)
            shiftedCol++
        }
        corners.sortBy { it.second.row }
        var shiftedRow = 0
        i = 0
        while (i <= corners.indices.last) {
            val row = corners[i].second.row
            do {
                if (corners[i].second.row == row) {
                    corners[i] = corners[i].copy(second = corners[i].second.copy(row = shiftedRow))
                } else {
                    break
                }
                i++
            } while (i <= corners.indices.last)
            shiftedRow++
        }
        val maxX = corners.maxOf { it.second.col }
        val maxY = corners.maxOf { it.second.row }
        val grid = (0..maxY).map {
            (0..maxX).map {
                ' '
            }.toMutableList()
        }
        val size = grid.toSize2()
        corners.sortBy { it.first }
        for (i in corners.indices) {
            val p1 = corners[i].second
            val p2 = corners[(i + 1).mod(corners.size)].second
            val d = p2 - p1
            if (d.col == 0) {
                for (k in (if (p2.row > p1.row) p1.row + 1..<p2.row else p2.row + 1..<p1.row)) {
                    grid[k][corners[i].second.col] = 'X'
                }
            } else if (d.row == 0) {
                for (k in (if (p2.col > p1.col) p1.col + 1..<p2.col else p2.col + 1..<p1.col)) {
                    grid[corners[i].second.row][k] = 'X'
                }
            } else {
                error("never")
            }
            grid[corners[i].second.row][corners[i].second.col] = '#'
        }
        val visited = mutableSetOf<Pos>()
        val fill = DeepRecursiveFunction { pos ->
            if (pos in visited) {
                return@DeepRecursiveFunction
            }
            when (grid[pos]) {
                '#', 'O', 'X' -> {
                    return@DeepRecursiveFunction
                }

                ' ' -> {
                    grid[pos] = '.'
                    visited.add(pos)
                }
            }
            pos.next(size).forEach { callRecursive(it) }
        }
        for (i in grid.indices) {
            val line = grid[i]
            fill(Pos(i, line.indices.first))
            fill(Pos(i, line.indices.last))
        }
        val line = grid.first()
        for (i in line.indices) {
            fill(Pos(grid.indices.first, i))
            fill(Pos(grid.indices.last, i))
        }
        for (line in grid) {
            for (i in line.indices) {
                if (line[i] == ' ') {
                    line[i] = 'X'
                }
            }
        }
        // println(grid.joinToString("\n") { line -> line.joinToString("") { it.toString() } })
        return areas.first { (i, k) ->
            val (_, pos1) = corners[i]
            val (_, pos2) = corners[k]
            val minRow = min(pos1.row, pos2.row)
            val maxRow = max(pos1.row, pos2.row)
            val minCol = min(pos1.col, pos2.col)
            val maxCol = max(pos1.col, pos2.col)
            for (row in minRow..maxRow) {
                for (col in minCol..maxCol) {
                    if (grid[row][col] == '.') {
                        return@first false
                    }
                }
            }
            true
        }.third
    }

    val testInput1 = readInput("Day9_1_test")
    check(testInput1.parse().part1() == 50L)
    check(testInput1.parse().part2() == 24L)
    val input = readInput("Day9")
    measureTimedValue { input.parse().part1() }.println()
    measureTimedValue { input.parse().part2() }.println()
}
