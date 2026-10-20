
fun main() {
    // "ООП", как будто, излишне, тут в целом не так много, но я все равно разбил и вынес.
    val answer = checker("src/main/kotlin/graph.txt")

    if (answer.isEmpty()) {
        println("нет вершин, достижимых из всех остальных")
    } else {
        println("вершины: $answer")
    }
}




