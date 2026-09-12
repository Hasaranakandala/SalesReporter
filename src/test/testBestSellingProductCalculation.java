void testCategoryRevenueCalculation() {
    CategoryRevenueCalculator calculator = new CategoryRevenueCalculator();
    Map<String, BigDecimal> results = calculator.calculate(sampleData);

    assertEquals(new BigDecimal("690.00"), results.get("Electronics"));
    assertEquals(new BigDecimal("181.25"), results.get("Stationery"));
}