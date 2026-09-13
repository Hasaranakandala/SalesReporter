package com.salesreporter.service;
import com.salesreporter.calculator.*;
import com.salesreporter.model.ProductSales;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReportGenerator {
    private final ProductRevenueCalculator productRevenueCalc = new ProductRevenueCalculator();
    private final CategoryRevenueCalculator categoryRevenueCalc = new CategoryRevenueCalculator();
    private final BestSellingProductCalculator bestSellingCalc = new BestSellingProductCalculator();
    private final HighestRevenueProductCalculator highestRevenueCalc = new HighestRevenueProductCalculator();
    private final GrandTotalRevenueCalculator grandTotalCalc = new GrandTotalRevenueCalculator();

    public String generateReport(List<ProductSales> salesData) {
        Map<ProductSales, BigDecimal> productRevenues = productRevenueCalc.calculate(salesData);
        Map<String, BigDecimal> categoryRevenues = categoryRevenueCalc.calculate(salesData);
        ProductSales bestSelling = bestSellingCalc.calculate(salesData);
        ProductSales highestRevenue = highestRevenueCalc.calculate(salesData);
        BigDecimal grandTotal = grandTotalCalc.calculate(salesData);

        StringBuilder sb = new StringBuilder();
        System.out.println();
        sb.append("\n============================================ ");
        sb.append("        PRODUCT SALES SUMMARY REPORT ");
        sb.append("============================================ \n");
        System.out.println("--- Revenue Per Product --- ");
        System.out.println();

        for (Map.Entry<ProductSales, BigDecimal> entry : productRevenues.entrySet()) {
            ProductSales p = entry.getKey();
            System.out.println();
            sb.append(String.format("%-6s %-20s %-12s $%7.2f%n",
                    p.getProductId(), p.getProductName(), p.getCategory(), entry.getValue()));
        }

        sb.append("--- Revenue Per Category --- ");
        for (Map.Entry<String, BigDecimal> entry : categoryRevenues.entrySet()) {
            sb.append(String.format("%-14s : $%7.2f%n", entry.getKey(), entry.getValue()));
        }

        sb.append("--- Highlights --- ");
        if (bestSelling != null) {
            sb.append(String.format("%-20s : %s (%d units)%n",
                    "Best-Selling Product", bestSelling.getProductName(), bestSelling.getQuantitySold()));
        } else {
            sb.append(String.format("%-20s : N/A%n", "Best-Selling Product"));
        }


        if (highestRevenue != null) {
            sb.append(String.format("%-20s : %s ($%.2f)%n",
                    "Highest Revenue", highestRevenue.getProductName(), highestRevenue.getTotalRevenue().doubleValue()));
        } else {
            sb.append(String.format("%-20s : N/A%n", "Highest Revenue"));
        }

        BigDecimal totalAmount = (grandTotal != null) ? grandTotal : BigDecimal.ZERO;
        sb.append(String.format("%-20s : $%.2f%n", "Grand Total Revenue", totalAmount.doubleValue()));

        sb.append("============================================\n");

        return sb.toString();
    }
}

