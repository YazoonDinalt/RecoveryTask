
// Функция проверки, возращает список вершин, получает на вход
fun checker(path: String): List<Int> {
    val rows = readIncidenceMatrix(path)

    val n = rows.size
    val m = rows[0].size
    println("вершин: $n, рёбер: $m")

    val reach = Array(n) { BooleanArray(n) }
    for (i in 0 until n) {
        reach[i][i] = true
    }

    for (e in 0 until m) {
        var from = -1
        var to = -1
        for (v in 0 until n) {
            if (rows[v][e] == -1)
                from = v
            if (rows[v][e] == 1)
                to = v
        }
        if (from != -1 && to != -1)
            reach[from][to] = true
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            if (reach[i][k]) {
                for (j in 0 until n) {
                    if (reach[k][j])
                        reach[i][j] = true
                }
            }
        }
    }

    val answer = ArrayList<Int>()
    for (v in 0 until n) {
        var reachableFromAll = true
        for (u in 0 until n) {
            if (!reach[u][v])
                reachableFromAll = false
        }
        if (reachableFromAll)
            answer.add(v)
    }

    return answer
}