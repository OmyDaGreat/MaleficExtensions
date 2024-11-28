package xyz.malefic.extensions.standard.file

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.malefic.extensions.standard.tree.TreeNode

/**
 * Asynchronously loads the content of the file and invokes the provided callback with the content.
 *
 * @param onContentLoaded A callback function that is invoked with the file content as a string.
 */
fun File.loadFileContent(onContentLoaded: (String) -> Unit) =
  CoroutineScope(Dispatchers.IO).launch {
    onContentLoaded(Files.readString(Paths.get(this@loadFileContent.toURI())))
  }

/**
 * Asynchronously saves the provided content to the file.
 *
 * @param content The content to be written to the file.
 */
fun File.saveFile(content: String) =
  CoroutineScope(Dispatchers.IO).launch {
    Files.writeString(Paths.get(this@saveFile.toURI()), content)
  }

/**
 * Recursively builds a file tree from a given file.
 *
 * This function traverses the directory structure starting from the given root file and constructs
 * a tree where each node represents a file or directory. The root node represents the initial file,
 * and its children represent the files and directories contained within it. This process is
 * repeated recursively for each directory.
 *
 * @return The root node of the file tree, where each node contains a file and its children.
 * @receiver The root file to build the tree from.
 */
fun File.buildFileTree(): TreeNode<File> =
  TreeNode(this, listFiles()?.map { it.buildFileTree() } ?: emptyList())
