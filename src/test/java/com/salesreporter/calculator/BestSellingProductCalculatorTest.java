package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class BestSellingProductCalculatorTest {

    public static void main(String[] args) {
        testBestSellingProductCalculation();
        System.out.println("✅ BestSellingProductCalculatorTest PASSED!");
    }

    public static void testBestSellingProductCalculation() {
        List<ProductSales> sampleData = Arrays.asList(
                new ProductSales("P001", "Wireless Mouse", "Electronics", 12, new BigDecimal("25.50")),
                new ProductSales("P002", "Notebook", "Stationery", 35, new BigDecimal("3.75")),
                new ProductSales("P003", "USB Hub", "Electronics", 8, new BigDecimal("18.00")),
                new ProductSales("P004", "Ballpoint Pen", "Stationery", 100, new BigDecimal("0.50")),
                new ProductSales("P005", "HDMI Cable", "Electronics", 20, new BigDecimal("12.00"))
        );

        BestSellingProductCalculator calculator = new BestSellingProductCalculator();
        ProductSales bestSelling = calculator.calculate(sampleData);

        if (bestSelling == null) {
            throw new AssertionError("Best selling product should not be null");
        }
        if (!"Ballpoint Pen".equals(bestSelling.getProductName())) {
            throw new AssertionError("Expected Ballpoint Pen but got " + bestSelling.getProductName());
        }
        if (bestSelling.getQuantitySold() != 100) {
            throw new AssertionError("Expected quantity 100 but got " + bestSelling.getQuantitySold());
        }
    }
}