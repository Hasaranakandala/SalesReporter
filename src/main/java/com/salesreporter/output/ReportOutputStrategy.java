package com.salesreporter.output;

public interface ReportOutputStrategy {
    void output(String reportContent) throws Exception;
}

