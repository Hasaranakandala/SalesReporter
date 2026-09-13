package com.salesreporter.output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileOutputStrategy implements ReportOutputStrategy {
    private final String destinationPath;

    public FileOutputStrategy(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    @Override
    public void output(String reportContent) throws IOException {
        Files.writeString(Paths.get(destinationPath), reportContent);
        System.out.println("Report successfully written to: " + destinationPath);
    }
}
