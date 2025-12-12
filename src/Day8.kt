import kotlin.time.measureTimedValue

private typealias Input8 = List<Vec3>
private typealias Result8 = Long

fun main() {
    fun List<String>.parse(): Input8 {
        return map { line ->
            val (x, y, z) = line.split(",").map { it.toInt() }
            Vec3(x, y, z)
        }
    }

    fun Input8.part1(connections: Int): Result8 {
        val pairs = buildList {
            for (i in this@part1.indices) {
                for (k in i + 1..this@part1.indices.last) {
                    val diff = this@part1[i] - this@part1[k]
                    add(Triple(diff.dot(diff), i, k))
                }
            }
        }
            .sortedBy { (distance) -> distance }
            .take(connections)
        var circuits = indices.map { setOf(it) }
        for (pair in pairs) {
            val (_, junctionBox1, junctionBox2) = pair
            val (join, rest) = circuits.partition { it.contains(junctionBox1) xor it.contains(junctionBox2) }
            if (join.isNotEmpty()) {
                circuits = (rest + listOf(join.reduce { acc, x -> acc + x }))
            }
        }
        return circuits.sortedByDescending { it.size }
            .take(3)
            .fold(1) { acc, v -> acc * v.size }
    }

    fun Input8.part2(): Result8 {
        val pairs = buildList {
            for (i in this@part2.indices) {
                for (k in i + 1..this@part2.indices.last) {
                    val diff = this@part2[i] - this@part2[k]
                    add(Triple(diff.dot(diff), i, k))
                }
            }
        }.sortedBy { (distance) -> distance }
        var circuits = indices.map { setOf(it) }
        for (pair in pairs) {
            val (_, junctionBox1, junctionBox2) = pair
            val (join, rest) = circuits.partition { it.contains(junctionBox1) xor it.contains(junctionBox2) }
            if (join.isNotEmpty()) {
                circuits = (rest + listOf(join.reduce { acc, x -> acc + x }))
                if (circuits.size == 1) {
                    return this@part2[junctionBox1].x.toLong() * this@part2[junctionBox2].x
                }
            }
        }
        error("never")
    }

    val testInput1 = readInput("Day8_1_test")
    check(testInput1.parse().part1(10) == 40L)
    check(testInput1.parse().part2() == 25272L)
    val input = readInput("Day8")
    measureTimedValue { input.parse().part1(1000) }.println()
    measureTimedValue { input.parse().part2() }.println()
}
