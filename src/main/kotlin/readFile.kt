import java.io.File

fun readIncidenceMatrix(path: String): List<IntArray> {
    val f = File(path)
    require(f.exists()) { "файл не найден: ${f.absolutePath}" }

    val rawLines = f.readLines()
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    require(rawLines.isNotEmpty()) { "файл пуст: ${f.absolutePath}" }

    val rows: List<IntArray> = rawLines.mapIndexed { idx, line ->
        try {
            line.split(Regex("\\s+")).map { it.toInt() }.toIntArray()
        } catch (e: NumberFormatException) {
            error("строка ${idx + 1}: не число в «$line»")
        }
    }

    val m = rows[0].size
    rows.forEachIndexed { idx, r ->
        require(r.size == m) {
            "строка ${idx + 1}: ${r.size} столбцов, а ожидалось $m"
        }
    }

    return rows
}