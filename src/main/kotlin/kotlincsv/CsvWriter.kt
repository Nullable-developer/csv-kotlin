package kotlincsv

import java.io.FileWriter
import java.io.BufferedWriter

import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class CsvWriter {
    private fun escapeCsvValue(value: String): String {
        return if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            "\"${value.replace("\"", "\"\"")}\""
        } else {
            value
        }
    }

    fun writeCsv(filePath: String, data: List<List<String>>): Unit {
        try {
            BufferedWriter(FileWriter(filePath)).use { writer ->
                data.forEach { row ->
                    writer.write(row.joinToString(",") { escapeCsvValue(it) })
                    writer.newLine()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun writeCsv(bufferedWriter: BufferedWriter, data: List<List<String>>): Unit {
        try {
            bufferedWriter.use { writer ->
                data.forEach { row ->
                    writer.write(row.joinToString(",") { escapeCsvValue(it) })
                    writer.newLine()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun asyncWriteCsv(filePath: String, data: List<List<String>>): Unit {
        withContext(Dispatchers.IO) {
            try {
                BufferedWriter(FileWriter(filePath)).use { writer ->
                    data.forEach { row ->
                        writer.write(row.joinToString(",") { escapeCsvValue(it) })
                        writer.newLine()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun asyncWriteCsv(bufferedWriter: BufferedWriter, data: List<List<String>>): Unit {
        withContext(Dispatchers.IO) {
            try {
                bufferedWriter.use { writer ->
                    data.forEach { row ->
                        writer.write(row.joinToString(",") { escapeCsvValue(it) })
                        writer.newLine()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}