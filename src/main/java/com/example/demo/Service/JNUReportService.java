package com.example.demo.Service;

import net.sf.jasperreports.engine.JRDataSource;

import java.util.Map;

/**
 *
 * @author Raphael Lee. Infomind Co.
 *
 *
 */
public interface JNUReportService {
    /**
     * Generates a HTML report with the given input file. Uses a JREmptyDataSource
     *
     * @param inputFileName
     *            report source file without extension
     * @param params
     *            report parameters
     * @return the byte[] containing the PDF
     */
    byte[] generatePdfReport(String inputFileName, Map<String, Object> params);


    /**
     * Generates a HTML report with the given input file. Uses a JREmptyDataSource
     *
     * @param inputFileName
     *            report source file without extension
     * @param params
     *            report parameters
     * @param dataSource
     *            the source of data
     * @return the byte[] containing the PDF
     */
    byte[] generatePdfReport(String inputFileName, Map<String, Object> params, JRDataSource dataSource);
}
