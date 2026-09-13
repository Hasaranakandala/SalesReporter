
package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.util.List;

public class BestSellingProductCalculator implements SalesMetricsCalculator<ProductSales> {

    @Override
    public ProductSales calculate(List<ProductSales> salesData) {

        if (salesData == null || salesData.isEmpty()) {
            return null;
        }


        ProductSales bestSellingProduct = salesData.get(0);


        for (int i = 1; i < salesData.size(); i++) {
            ProductSales currentProduct = salesData.get(i);


            if (currentProduct.getQuantitySold() > bestSellingProduct.getQuantitySold()) {
                bestSellingProduct = currentProduct;
            }
        }

        return bestSellingProduct;
    }
}