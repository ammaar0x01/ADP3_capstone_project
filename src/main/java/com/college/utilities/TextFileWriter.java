package com.college.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextFileWriter {

    private final String filePath;

    public TextFileWriter(String filePath) {
        this.filePath = filePath;
    }

    // Method to write a single line to the file
    public void writeLine(String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine(); // Add a newline after writing
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Method to write multiple lines to the file
    public void writeLines(List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }


    // --------------------------------------
    public static void main(String[] args) {
        TextFileWriter writer = new TextFileWriter("records/output.txt");
        writer.writeLine("Hello, world!");
        System.out.println("Successfully wrote to file");

        //        writer.writeLines(Arrays.asList("Line 1", "Line 2", "Line 3"));
    }

}
