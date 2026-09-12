package com.salesreporter.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductSales {
    private final String productId;
    private final String productName;
    private final String category;
    private final int quantitySold;
    private final BigDecimal unitPrice;

    public ProductSales(String productId, String productName, String category, int quantitySold, BigDecimal unitPrice) {
        this.productId = Objects.requireNonNull(productId, "productId cannot be null");
        this.productName = Objects.requireNonNull(productName, "productName cannot be null");
        this.category = Objects.requireNonNull(category, "category cannot be null");
        this.quantitySold = quantitySold;
        this.unitPrice = Objects.requireNonNull(unitPrice, "unitPrice cannot be null");
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public int getQuantitySold() { return quantitySold; }
    public BigDecimal getUnitPrice() { return unitPrice; }

    public BigDecimal getTotalRevenue() {
        return unitPrice.multiply(BigDecimal.valueOf(quantitySold));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSales that = (ProductSales) o;
        return quantitySold == that.quantitySold &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(category, that.category) &&
                Objects.equals(unitPrice, that.unitPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, category, quantitySold, unitPrice);
    }
}
