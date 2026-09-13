
package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.List;

public class HighestRevenueProductCalculator implements SalesMetricsCalculator<ProductSales> {

    @Override
    public ProductSales calculate(List<ProductSales> salesData) {

        if (salesData == null || salesData.isEmpty()) {
            return null;
        }


        ProductSales highestRevenueProduct = salesData.get(0);


        for (ProductSales sale : salesData) {
            BigDecimal currentRevenue = sale.getTotalRevenue();
            BigDecimal maxRevenue = highestRevenueProduct.getTotalRevenue();


            if (currentRevenue != null && currentRevenue.compareTo(maxRevenue) > 0) {
                highestRevenueProduct = sale;
            }
        }

        return highestRevenueProduct;
    }
}