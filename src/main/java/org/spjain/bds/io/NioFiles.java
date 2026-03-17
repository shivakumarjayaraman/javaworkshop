package org.spjain.bds.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Demonstrates the modern Java NIO (New IO) API introduced in Java 7+.
 *
 * The old IO (File class) had many problems: no useful exceptions, inconsistent
 * behavior across operating systems, missing features. NIO fixes all of this.
 *
 * Two key classes:
 *   Path      -> represents a file or directory location (replaces java.io.File)
 *   Files     -> utility class with static methods for common operations
 *
 * Python equivalent:
 *   pathlib.Path  ->  Path
 *   open() / os  ->  Files
 */
public class NioFiles {

    private static final Path FILE_PATH = Path.of("/tmp/nio_demo.txt");
    private static final Path DIR_PATH  = Path.of("/tmp/nio_demo_dir");

    // Write a string directly to a file — much simpler than FileWriter + BufferedWriter
    private static void writeString(Path path) throws IOException {
        String content = "Hello from NIO!\nLine two\nLine three\n";
        Files.writeString(path, content);
        System.out.println("Wrote: " + path);
    }

    // Read the entire file back as a single String
    private static void readString(Path path) throws IOException {
        String content = Files.readString(path);
        System.out.println("File contents:\n" + content);
    }

    // Write a list of lines (Files adds the line separators automatically)
    private static void writeLines(Path path) throws IOException {
        List<String> lines = List.of(
            "Alice,Engineering",
            "Bob,Marketing",
            "Charlie,Engineering",
            "Diana,HR"
        );
        Files.write(path, lines);   // StandardOpenOption.CREATE is default
        System.out.println("Wrote " + lines.size() + " lines to " + path);
    }

    // Read all lines into a List<String> at once
    private static void readAllLines(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path);
        System.out.println("\nAll lines (" + lines.size() + " total):");
        for (String line : lines) {
            System.out.println("  " + line);
        }
    }

    // Filter lines using streams — combines NIO with the Streams API
    private static void filterLines(Path path, String department) throws IOException {
        System.out.println("\nFiltering for department: " + department);
        Files.lines(path)                         // returns a Stream<String>, reads lazily
             .filter(line -> line.endsWith(department))
             .forEach(System.out::println);
    }

    // Useful Path operations — Path is not just a string, it understands file system structure
    private static void showPathInfo(Path path) {
        System.out.println("\nPath info for: " + path);
        System.out.println("  File name : " + path.getFileName());
        System.out.println("  Parent dir: " + path.getParent());
        System.out.println("  Absolute  : " + path.toAbsolutePath());
        System.out.println("  Exists    : " + Files.exists(path));
    }

    // Create directories and copy files
    private static void createAndCopy(Path sourceFile, Path targetDir) throws IOException {
        Files.createDirectories(targetDir);                // creates dir (and parents) if missing
        Path destination = targetDir.resolve("copy.txt"); // resolve() builds a child path
        Files.copy(sourceFile, destination, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("\nCopied " + sourceFile + " -> " + destination);
    }

    // Walk a directory tree and list all files
    private static void walkDirectory(Path dir) throws IOException {
        System.out.println("\nFiles under " + dir + ":");
        try (Stream<Path> paths = Files.walk(dir)) {
            paths.filter(Files::isRegularFile)
                 .forEach(p -> System.out.println("  " + p));
        }
    }

    public static void main(String[] args) throws IOException {
        // --- 1. Simple write and read ---
        writeString(FILE_PATH);
        readString(FILE_PATH);

        // --- 2. Write lines, read lines, filter with streams ---
        writeLines(FILE_PATH);
        readAllLines(FILE_PATH);
        filterLines(FILE_PATH, "Engineering");

        // --- 3. Path info ---
        showPathInfo(FILE_PATH);

        // --- 4. Create a directory, copy a file, walk the tree ---
        createAndCopy(FILE_PATH, DIR_PATH);
        walkDirectory(DIR_PATH);
    }
}
