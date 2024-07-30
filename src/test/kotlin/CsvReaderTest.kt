import kotlincsv.CsvReader

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.test.runTest

import java.io.File

class CsvReaderTest {
    private val csvReader: CsvReader = CsvReader()

    @Test
    fun `sync read file by path`() {
        val result = csvReader.readCsv("index.csv")

        val excepted = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        assertEquals(excepted, result)
    }

    @Test
    fun `sync read file by File`() {
        val result = csvReader.readCsv(File("index.csv"))

        val excepted = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        assertEquals(excepted, result)
    }

    @OptIn(ExperimentalTime::class)
    @Test
     fun `async read file by path`() = runTest {
        val result = csvReader.asyncReadCsv("index.csv")

        val excepted = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        assertEquals(excepted, result)
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `async read file by File`() = runTest {
        val result = csvReader.asyncReadCsv(File("index.csv"))

        val excepted = listOf(
            listOf("name", "age", "city"),
            listOf("John", "30", "New York")
        )

        assertEquals(excepted, result)
    }
}