import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CheckerTest {

    private fun writeTemp(content: String): String {
        val f = File.createTempFile("graph", ".txt")
        f.writeText(content)
        f.deleteOnExit()
        return f.absolutePath
    }

    @Test
    fun `вершина достижима из всех`() {
        // 0 -> 1 -> 2, значит 2 достижима из всех
        val path = writeTemp(
            """
            -1  0  0
             1 -1  0
             0  1 -1
            """.trimIndent()
        )
        assertEquals(listOf(2), checker(path))
    }

    @Test
    fun `нет вершины достижимой из всех`() {
        val path = writeTemp(
            """
            -1  1  0
             1 -1  0
             0  0  0
            """.trimIndent()
        )
        assertEquals(emptyList(), checker(path))
    }

    @Test
    fun `кратные ребра не ломают результат`() {
        val path = writeTemp(
            """
            -1 -1  0  0
             1  1 -1  0
             0  0  1 -1
            """.trimIndent()
        )
        assertEquals(listOf(2), checker(path))
    }

    @Test
    fun `единственная вершина достижима сама из себя`() {
        val path = writeTemp("0")
        assertEquals(listOf(0), checker(path))
    }

    @Test
    fun `пустой файл вызывает ошибку`() {
        val path = writeTemp("")
        assertFailsWith<IllegalArgumentException> {
            checker(path)
        }
    }

    @Test
    fun `нечисловые данные вызывают ошибку`() {
        val path = writeTemp(
            """
            -1  abc
             1    0
            """.trimIndent()
        )
        assertFailsWith<IllegalStateException> {
            checker(path)
        }
    }

    @Test
    fun `разная длина строк вызывает ошибку`() {
        val path = writeTemp(
            """
            -1  0
             1
            """.trimIndent()
        )
        assertFailsWith<IllegalArgumentException> {
            checker(path)
        }
    }
}