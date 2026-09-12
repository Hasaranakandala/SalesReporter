
package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProductRevenueCalculator implements SalesMetricsCalculator<Map<ProductSales, BigDecimal>> {

    @Override
    public Map<ProductSales, BigDecimal> calculate(List<ProductSales> salesData) {

        if (salesData == null || salesData.isEmpty()) {
            return new LinkedHashMap<>();
        }


        Map<ProductSales, BigDecimal> productRevenueMap = new LinkedHashMap<>();


        for (ProductSales sale : salesData) {
            if (sale != null) {
                productRevenueMap.put(sale, sale.getTotalRevenue());
            }
        }

        return productRevenueMap;
    }
}