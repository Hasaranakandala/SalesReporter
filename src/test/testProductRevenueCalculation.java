void testProductRevenueCalculation() {
    ProductRevenueCalculator calculator = new ProductRevenueCalculator();
    Map<ProductSales, BigDecimal> results = calculator.calculate(sampleData);

    assertEquals(new BigDecimal("306.00"), results.get(sampleData.get(0)));
    assertEquals(new BigDecimal("131.25"), results.get(sampleData.get(1)));
    assertEquals(new BigDecimal("144.00"), results.get(sampleData.get(2)));
    assertEquals(new BigDecimal("50.00"), results.get(sampleData.get(3)));
    assertEquals(new BigDecimal("240.00"), results.get(sampleData.get(4)));
}