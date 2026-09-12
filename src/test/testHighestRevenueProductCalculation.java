void testHighestRevenueProductCalculation() {
    HighestRevenueProductCalculator calculator = new HighestRevenueProductCalculator();
    ProductSales highestRevenue = calculator.calculate(sampleData);

    assertNotNull(highestRevenue, "Highest revenue product should not be null");
    assertEquals("Laptop", highestRevenue.getProductName());
}