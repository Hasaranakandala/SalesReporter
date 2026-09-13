package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CategoryRevenueCalculatorTest {

    public static void main(String[] args) {
        testCategoryRevenueCalculation();
        System.out.println("✅ CategoryRevenueCalculatorTest PASSED!");
    }

    public static void testCategoryRevenueCalculation() {
        List<ProductSales> sampleData = Arrays.asList(
                new ProductSales("P001", "Wireless Mouse", "Electronics", 10, new BigDecimal("20.00")), // 200.00
                new ProductSales("P002", "Notebook", "Stationery", 5, new BigDecimal("10.00")),      // 50.00
                new ProductSales("P003", "USB Hub", "Electronics", 2, new BigDecimal("50.00")),       // 100.00
                new ProductSales("P004", "Ballpoint Pen", "Stationery", 20, new BigDecimal("2.00"))    // 40.00
        );

        CategoryRevenueCalculator calculator = new CategoryRevenueCalculator();
        Map<String, BigDecimal> categoryTotals = calculator.calculate(sampleData);

        if (categoryTotals == null) {
            throw new AssertionError("Category totals map should not be null");
        }

        BigDecimal electronicsTotal = categoryTotals.get("Electronics");
        BigDecimal stationeryTotal = categoryTotals.get("Stationery");

        if (electronicsTotal == null || electronicsTotal.compareTo(new BigDecimal("300.00")) != 0) {
            throw new AssertionError("Expected Electronics total 300.00 but got " + electronicsTotal);
        }
        if (stationeryTotal == null || stationeryTotal.compareTo(new BigDecimal("90.00")) != 0) {
            throw new AssertionError("Expected Stationery total 90.00 but got " + stationeryTotal);
        }
    }
}