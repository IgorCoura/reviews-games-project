package br.com.imt.component

import br.com.imt.interfaces.IDaoGames
import br.com.imt.interfaces.IDaoUser
import io.ktor.http.content.*
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths

object FileComponent {

    private val root = System.getProperty("user.dir")

    fun recoverFile(path: String): File{
        val filePath = Paths.get(root, path)
        return try {
            val file = File(filePath.toString())
            return file
        } catch (e: FileNotFoundException) {
            throw RuntimeException("Path " + path + "not found. Error: " + e.message)
        }
    }

    suspend fun saveFile(multipartData: MultiPartData, path: String): String{
        var fileName = ""
        multipartData.forEachPart { part ->
            when (part) {
                is PartData.FileItem -> {
                    fileName = part.originalFileName as String
                    val filePath = Paths.get(root,path)
                    Files.createDirectories(filePath)
                    var fileBytes = part.streamProvider().readBytes()
                    File("$filePath/$fileName").writeBytes(fileBytes)
                }
            }
        }
        return fileName
    }
}