package com.salesreporter;

import com.salesreporter.calculator.*;
import com.salesreporter.model.ProductSales;
import com.salesreporter.reader.CsvSalesDataReader;
import com.salesreporter.service.ReportGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CsvSalesDataReader reader = new CsvSalesDataReader();


        List<ProductSales> sampleSales;

        try {
            String filePath = "C:\\Users\\ASUS TUF\\Desktop\\SalesReporterProject\\sales-reporter\\sales_sample.csv";
            sampleSales = reader.readSalesData(filePath);
        } catch (IOException e) {
            System.err.println("fail read CSV file: " + e.getMessage());
            return;
        }


        if (sampleSales == null || sampleSales.isEmpty()) {
            System.out.println("No sales data found in the CSV file.");
            return;
        }


        SalesMetricsCalculator<BigDecimal> grandTotalCalc = new GrandTotalRevenueCalculator();
        SalesMetricsCalculator<Map<String, BigDecimal>> categoryRevenueCalc = new CategoryRevenueCalculator();
        SalesMetricsCalculator<ProductSales> bestSellingCalc = new BestSellingProductCalculator();
        SalesMetricsCalculator<ProductSales> highestRevenueCalc = new HighestRevenueProductCalculator();
        SalesMetricsCalculator<Map<ProductSales, BigDecimal>> productRevenueCalc = new ProductRevenueCalculator();


        BigDecimal grandTotal = grandTotalCalc.calculate(sampleSales);
        Map<String, BigDecimal> categoryRevenue = categoryRevenueCalc.calculate(sampleSales);
        ProductSales bestSelling = bestSellingCalc.calculate(sampleSales);
        ProductSales highestRevenue = highestRevenueCalc.calculate(sampleSales);
        Map<ProductSales, BigDecimal> productRevenue = productRevenueCalc.calculate(sampleSales);


        System.out.println("============================================");
        System.out.println("          SALES REPORT SUMMARY              ");
        System.out.println("============================================");

        System.out.printf("%-22s : $%.2f%n", "Grand Total Revenue", grandTotal);

        if (bestSelling != null) {
            System.out.printf("%-22s : %s (%d units)%n", "Best-Selling Product",
                    bestSelling.getProductName(), bestSelling.getQuantitySold());
        }

        if (highestRevenue != null) {
            System.out.printf("%-22s : %s ($%.2f)%n", "Highest Revenue Product",
                    highestRevenue.getProductName(), highestRevenue.getTotalRevenue().doubleValue());
        }

        System.out.println("\n--------------------------------------------");
        System.out.println(" Category Revenue Breakdown ");
        System.out.println("--------------------------------------------");
        for (Map.Entry<String, BigDecimal> entry : categoryRevenue.entrySet()) {
            System.out.printf("%-20s : $%.2f%n", entry.getKey(), entry.getValue().doubleValue());
        }

        System.out.println("\n--------------------------------------------");
        System.out.println(" Individual Product Revenues ");
        System.out.println("--------------------------------------------");
        for (Map.Entry<ProductSales, BigDecimal> entry : productRevenue.entrySet()) {
            System.out.printf("%-20s : $%.2f%n", entry.getKey().getProductName(), entry.getValue().doubleValue());
        }

        System.out.println(" Report Generator ! ");

        ReportGenerator reportGenerator = new ReportGenerator();


        String finalReport = reportGenerator.generateReport(sampleSales);


        System.out.println(finalReport);




        System.out.println("============================================");
    }
}
