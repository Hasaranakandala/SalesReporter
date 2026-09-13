package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ProductRevenueCalculatorTest {

    public static void main(String[] args) {
        testProductRevenueCalculation();
        System.out.println("✅ ProductRevenueCalculatorTest PASSED!");
    }

    public static void testProductRevenueCalculation() {
        ProductSales p1 = new ProductSales("P001", "Wireless Mouse", "Electronics", 10, new BigDecimal("25.00"));
        ProductSales p2 = new ProductSales("P002", "Notebook", "Stationery", 40, new BigDecimal("2.50"));

        List<ProductSales> sampleData = Arrays.asList(p1, p2);

        ProductRevenueCalculator calculator = new ProductRevenueCalculator();


        Map<ProductSales, BigDecimal> productTotals = calculator.calculate(sampleData);

        if (productTotals == null) {
            throw new AssertionError("Product totals map should not be null");
        }

        BigDecimal mouseTotal = productTotals.get(p1);
        BigDecimal notebookTotal = productTotals.get(p2);

        if (mouseTotal == null || mouseTotal.compareTo(new BigDecimal("250.00")) != 0) {
            throw new AssertionError("Expected Wireless Mouse total 250.00 but got " + mouseTotal);
        }
        if (notebookTotal == null || notebookTotal.compareTo(new BigDecimal("100.00")) != 0) {
            throw new AssertionError("Expected Notebook total 100.00 but got " + notebookTotal);
        }
    }
}