package com.salesreporter.calculator;

import com.salesreporter.model.ProductSales;
import java.util.List;

public interface SalesMetricsCalculator<CalculationResult> {
    CalculationResult calculate(List<ProductSales> salesData);
}
