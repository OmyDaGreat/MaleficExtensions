package xyz.malefic.ext.file

import java.io.File
import java.nio.file.Files
import kotlin.io.path.createTempDirectory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FileTest {
    // Building tree from directory with multiple files and subdirectories
    @Test
    fun build_tree_from_directory_with_multiple_files_and_subdirs() {
        val rootDir = createTempDirectory().toFile()
        val subDir = File(rootDir, "subdir").apply { mkdir() }
        File(rootDir, "file1.txt").createNewFile()
        File(subDir, "file2.txt").createNewFile()

        val tree = rootDir.buildFileTree()

        assertEquals(rootDir, tree.value)
        assertEquals(2, tree.children.size)
        assertTrue(tree.children.any { it.value.name == "subdir" })
        assertTrue(tree.children.any { it.value.name == "file1.txt" })
        val subDirNode = tree.children.first { it.value.name == "subdir" }
        assertEquals(1, subDirNode.children.size)
        assertEquals("file2.txt", subDirNode.children[0].value.name)

        rootDir.deleteRecursively()
    }

    // Building tree from empty directory
    @Test
    fun build_tree_from_empty_directory() {
        val emptyDir = createTempDirectory().toFile()

        val tree = emptyDir.buildFileTree()

        assertEquals(emptyDir, tree.value)
        assertTrue(tree.children.isEmpty())

        emptyDir.delete()
    }

    // Building tree from single file
    @Test
    fun build_tree_from_single_file() {
        val file = File.createTempFile("test", ".txt")

        val tree = file.buildFileTree()

        assertEquals(file, tree.value)
        assertTrue(tree.children.isEmpty())

        file.delete()
    }

    // Verify correct parent-child relationships in generated tree structure
    @Test
    fun verify_parent_child_relationships_in_tree() {
        val rootDir = createTempDirectory().toFile()
        val subDir1 = File(rootDir, "subdir1").apply { mkdir() }
        val subDir2 = File(subDir1, "subdir2").apply { mkdir() }
        File(subDir2, "file.txt").createNewFile()

        val tree = rootDir.buildFileTree()

        assertEquals(rootDir, tree.value)
        val subDir1Node = tree.children.single()
        assertEquals(subDir1, subDir1Node.value)
        val subDir2Node = subDir1Node.children.single()
        assertEquals(subDir2, subDir2Node.value)
        assertEquals(
            "file.txt",
            subDir2Node.children
                .single()
                .value.name,
        )

        rootDir.deleteRecursively()
    }

    // Tree structure maintains original file hierarchy
    @Test
    fun tree_structure_maintains_file_hierarchy() {
        val rootDir = createTempDirectory().toFile()
        val files =
            listOf(
                "a/b/file1.txt",
                "a/file2.txt",
                "file3.txt",
            )

        files.forEach { path ->
            val file = File(rootDir, path)
            file.parentFile.mkdirs()
            file.createNewFile()
        }

        val tree = rootDir.buildFileTree()
        val paths = tree.flattenTree().map { it.first.relativeTo(rootDir).path }

        assertEquals(
            setOf(
                "",
                "a",
                "a${File.separator}b",
                "a${File.separator}b${File.separator}file1.txt",
                "a${File.separator}file2.txt",
                "file3.txt",
            ),
            paths.toSet(),
        )

        rootDir.deleteRecursively()
    }

    // Building tree from non-existent file/directory
    @Test
    fun build_tree_from_non_existent_file() {
        val nonExistentFile = File("/non/existent/path")

        val tree = nonExistentFile.buildFileTree()

        assertEquals(nonExistentFile, tree.value)
        assertTrue(tree.children.isEmpty())
    }

    // Building tree from file with insufficient permissions
    @Test
    fun build_tree_from_restricted_access_directory() {
        val restrictedDir = createTempDirectory().toFile()
        restrictedDir.setReadable(false)

        val tree = restrictedDir.buildFileTree()

        assertEquals(restrictedDir, tree.value)
        assertTrue(tree.children.isEmpty())

        restrictedDir.setReadable(true)
        restrictedDir.delete()
    }

    // Building tree from directory containing symlinks
    @Test
    fun build_tree_from_directory_with_symlinks() {
        val rootDir = createTempDirectory().toFile()
        val targetDir = File(rootDir, "target").apply { mkdir() }
        File(targetDir, "file.txt").createNewFile()
        val linkDir = File(rootDir, "link")
        Files.createSymbolicLink(linkDir.toPath(), targetDir.toPath())

        val tree = rootDir.buildFileTree()

        assertEquals(rootDir, tree.value)
        assertEquals(2, tree.children.size)
        assertTrue(tree.children.any { it.value.name == "target" })
        assertTrue(tree.children.any { it.value.name == "link" })

        rootDir.deleteRecursively()
    }

    // Building tree from directory with special characters in filenames
    @Test
    fun build_tree_from_directory_with_special_chars() {
        val rootDir = createTempDirectory().toFile()
        val specialNames = listOf("file@.txt", "空白.txt", "file#1.txt")

        specialNames.forEach { name ->
            File(rootDir, name).createNewFile()
        }

        val tree = rootDir.buildFileTree()

        assertEquals(rootDir, tree.value)
        assertEquals(specialNames.size, tree.children.size)
        specialNames.forEach { name ->
            assertTrue(tree.children.any { it.value.name == name })
        }

        rootDir.deleteRecursively()
    }

    // Building tree from very deep directory structure
    @Test
    fun build_tree_from_deep_directory_structure() {
        val rootDir = createTempDirectory().toFile()
        var currentDir = rootDir
        val depth = 50

        repeat(depth) { level ->
            currentDir = File(currentDir, "level_$level").apply { mkdir() }
        }

        val tree = rootDir.buildFileTree()
        val maxDepth = tree.flattenTree().maxOf { it.second }

        assertEquals(depth, maxDepth)

        rootDir.deleteRecursively()
    }
}
