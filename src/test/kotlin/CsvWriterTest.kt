import kotlincsv.CsvWriter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.test.runTest

import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter

class CsvWriterTest {
    private val csvWriter: CsvWriter = CsvWriter()

    @Test
    fun `sync write file by path`() {
        val data = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        csvWriter.writeCsv("index.csv", data)

        val file = File("index.csv")

        val writtenData = file.readLines()
        val exceptedData = data.map { it.joinToString(",") }

        assertEquals(writtenData, exceptedData)
        file.delete()
    }

    @Test
    fun `sync write file by BufferWriter`() {
        val data = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        val bufferWriter = BufferedWriter(FileWriter("index.csv"))

        val file = File("index.csv")

        csvWriter.writeCsv(bufferWriter, data)

        val writtenData = file.readLines()
        val exceptedData = data.map { it.joinToString(",") }

        assertEquals(writtenData, exceptedData)
        file.delete()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `async write file by path`() = runTest {
        val data = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        csvWriter.asyncWriteCsv("index.csv", data)

        val file = File("index.csv")

        val writtenData = file.readLines()
        val exceptedData = data.map { it.joinToString(",") }

        assertEquals(writtenData, exceptedData)
        file.delete()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `async write file by BufferWriter`() = runTest {
        val data = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        val bufferWriter = BufferedWriter(FileWriter("index.csv"))

        val file = File("index.csv")

        csvWriter.asyncWriteCsv(bufferWriter, data)

        val writtenData = file.readLines()
        val exceptedData = data.map { it.joinToString(",") }

        assertEquals(writtenData, exceptedData)
        file.delete()
    }
}