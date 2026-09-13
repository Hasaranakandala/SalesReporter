package com.salesreporter;

import com.salesreporter.model.ProductSales;
import com.salesreporter.output.ConsoleOutputStrategy;
import com.salesreporter.output.FileOutputStrategy;
import com.salesreporter.output.ReportOutputStrategy;
import com.salesreporter.reader.CsvSalesDataReader;
import com.salesreporter.service.ReportGenerator;

import java.io.FileNotFoundException;
import java.util.List;

public class SalesReporter {

    public static void main(String[] args) {
        if (args.length < 2) {
            printErrorAndExit("Error: Insufficient parameters provided.");
        }

        String csvFilePath = args[0];
        String outputMethod = args[1].toLowerCase();
        String outputFilePath = args.length >= 3 ? args[2] : null;

        try {
            ReportOutputStrategy outputStrategy = resolveOutputStrategy(outputMethod, outputFilePath);
            CsvSalesDataReader reader = new CsvSalesDataReader();
            List<ProductSales> salesData = reader.readSalesData(csvFilePath);

            ReportGenerator generator = new ReportGenerator();
            String report = generator.generateReport(salesData);

            outputStrategy.output(report);

        } catch (FileNotFoundException e) {
            System.err.println("Error: CSV file not found at path -> " + csvFilePath);
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.println("Data Format Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Execution Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static ReportOutputStrategy resolveOutputStrategy(String method, String filePath) {
        switch (method) {
            case "console":
                return new ConsoleOutputStrategy();
            case "file":
                if (filePath == null || filePath.trim().isEmpty()) {
                    printErrorAndExit("Error: Output file path is required when output-method is 'file'.");
                }
                return new FileOutputStrategy(filePath);
            default:
                printErrorAndExit("Error: Invalid output-method '" + method + "'. Supported options: 'console', 'file'.");
                return null;
        }
    }

    private static void printErrorAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println("Usage: java SalesReporter <csv-file-path> <output-method [console|file]> [output-file-path]");
        System.exit(1);
    }
}
