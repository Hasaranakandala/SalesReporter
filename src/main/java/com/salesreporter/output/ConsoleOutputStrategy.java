package com.salesreporter.output;

public class ConsoleOutputStrategy implements ReportOutputStrategy {
    @Override
    public void output(String reportContent) {
        System.out.println(reportContent);
    }
}
