package com.careerit.configurations;

import com.careerit.configurations.report.ReportService;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ReportServiceTest {

        @Test
        void downloadExcelFile() throws IOException {
            ReportService reportService = new ReportService();
            reportService.downloadExcelReport("BCJ1");
        }
}
