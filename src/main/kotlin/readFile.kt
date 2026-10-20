import java.io.File

// Функция чтения из файла, чуть
fun readIncidenceMatrix(path: String): List<IntArray> {
    val f = File(path)
    require(f.exists()) { "файл не найден: ${f.absolutePath}" }

    val rawLines = f.readLines()
        .map { it.trim() }
        .filter { it.isNotEmpty() }

    require(rawLines.isNotEmpty()) { "файл пуст: ${f.absolutePath}" }

    val rows: List<IntArray> = rawLines.mapIndexed { idx, line ->
        line.split(Regex("\\s+")).map { it.toInt() }.toIntArray()
    }

    val m = rows[0].size
    rows.forEachIndexed { idx, r ->
        require(r.size == m) {
            "строка ${idx + 1}: ${r.size} столбцов, а ожидалось $m"
        }
    }

    return rows
}