package com.salesreporter.reader;

import com.salesreporter.model.ProductSales;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CsvSalesDataReader {
    public List<ProductSales> readSalesData(String filePath) throws IOException {
        List<ProductSales> salesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                if (isHeader) {
                    isHeader = false; // Skip CSV header row
                    continue;
                }

                String[] tokens = line.split(",");
                if (tokens.length < 5) {
                    throw new IllegalArgumentException("Invalid format at line " + lineNumber + ": Required 5 columns, found " + tokens.length);
                }

                try {
                    String productId = tokens[0].trim();
                    String productName = tokens[1].trim();
                    String category = tokens[2].trim();
                    int quantitySold = Integer.parseInt(tokens[3].trim());
                    BigDecimal unitPrice = new BigDecimal(tokens[4].trim());

                    salesList.add(new ProductSales(productId, productName, category, quantitySold, unitPrice));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Data error at line " + lineNumber + ": Invalid numeric values.");
                }
            }
        }

        if (salesList.isEmpty()) {
            throw new IllegalArgumentException("The input CSV file contains no valid sales records.");
        }

        return salesList;
    }
}
