package kotlincsv

import java.io.File

import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

class CsvReader {
    fun readCsv(filePath: String): List<List<String>> {
        return File(filePath).bufferedReader().useLines { lines ->
            lines.map {
                it.split(",")
            }.toList()
        }
    }

    fun readCsv(file: File): List<List<String>> {
        return file.bufferedReader().useLines { lines ->
            lines.map {
                it.split(",")
            }.toList()
        }
    }

    suspend fun asyncReadCsv(filePath: String): List<List<String>> {
        return withContext(Dispatchers.IO) {
            File(filePath).bufferedReader().useLines { lines ->
                lines.map {
                    it.split(",")
                }.toList()
            }
        }
    }

    suspend fun asyncReadCsv(file: File): List<List<String>> {
        return withContext(Dispatchers.IO) {
            file.bufferedReader().useLines { lines ->
                lines.map {
                    it.split(",")
                }.toList()
            }
        }
    }
}