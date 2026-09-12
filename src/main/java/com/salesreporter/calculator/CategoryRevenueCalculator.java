

package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CategoryRevenueCalculator implements SalesMetricsCalculator<Map<String, BigDecimal>> {

    @Override
    public Map<String, BigDecimal> calculate(List<ProductSales> salesData) {

        if (salesData == null || salesData.isEmpty()) {
            return new LinkedHashMap<>();
        }


        Map<String, BigDecimal> categoryRevenueMap = new LinkedHashMap<>();


        for (ProductSales sale : salesData) {
            String category = sale.getCategory();
            BigDecimal revenue = sale.getTotalRevenue();

           
            if (categoryRevenueMap.containsKey(category)) {

                BigDecimal currentTotal = categoryRevenueMap.get(category);
                BigDecimal updatedTotal = currentTotal.add(revenue);
                categoryRevenueMap.put(category, updatedTotal);
            } else {

                categoryRevenueMap.put(category, revenue);
            }
        }

        return categoryRevenueMap;
    }
}