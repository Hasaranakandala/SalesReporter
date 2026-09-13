package com.salesreporter.reader;

import com.salesreporter.model.ProductSales;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

    public class CsvSalesDataReader {

        public CsvSalesDataReader() {
        }

        public List<ProductSales> readSalesData(String filePath) throws IOException {
            List<ProductSales> salesList = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isHeader = true;

                while ((line = reader.readLine()) != null) {
                    // Header line එක Skip කිරීම
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }

                    String[] data = line.split(",");
                    if (data.length >= 5) {
                        String productId = data[0].trim();
                        String productName = data[1].trim();
                        String category = data[2].trim();
                        int quantitySold = Integer.parseInt(data[3].trim());
                        BigDecimal unitPrice = new BigDecimal(data[4].trim());

                        salesList.add(new ProductSales(productId, productName, category, quantitySold, unitPrice));
                    }
                }
            }

            return salesList;
        }
    }