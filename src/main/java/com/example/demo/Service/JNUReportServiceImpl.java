package com.example.demo.Service;

import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
// TODO ReportService 리팩토링 작업중....(과연 이게 효율적인가?)
@Slf4j
public class JNUReportServiceImpl implements JNUReportService {

    @Override
    public byte[] generatePdfReport(String inputFileName, Map<String, Object> params) {
        return generatePdfReport(inputFileName, params, new JREmptyDataSource());
    }

    @Override
    public byte[] generatePdfReport(String inputFileName, Map<String, Object> params, JRDataSource dataSource) {
        byte[] bytes = null;
        JasperReport jasperReport = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Encountered error when loading jasper file", e);
        }

        return bytes;
    }
}
