package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.List;

public class GrandTotalRevenueCalculator implements SalesMetricsCalculator<BigDecimal> {

    @Override
    public BigDecimal calculate(List<ProductSales> salesData) {

        if (salesData == null || salesData.isEmpty()) {
            return BigDecimal.ZERO;
        }


        BigDecimal grandTotal = BigDecimal.ZERO;


        for (ProductSales sale : salesData) {
            if (sale.getTotalRevenue() != null) {
                grandTotal = grandTotal.add(sale.getTotalRevenue());
            }
        }

        return grandTotal;
    }
}

