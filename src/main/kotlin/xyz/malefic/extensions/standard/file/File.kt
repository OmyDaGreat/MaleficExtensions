package xyz.malefic.extensions.standard.file

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Loads the content of the file.
 *
 * @param onContentLoaded Lambda function to handle the loaded content.
 */
fun File.loadFileContent(onContentLoaded: (String) -> Unit) =
  CoroutineScope(Dispatchers.IO).launch {
    onContentLoaded(Files.readString(Paths.get(this@loadFileContent.toURI())))
  }

/**
 * Saves the content to the file.
 *
 * @param content The content to save.
 */
fun File.saveFile(content: String) =
  CoroutineScope(Dispatchers.IO).launch {
    Files.writeString(Paths.get(this@saveFile.toURI()), content)
  }
