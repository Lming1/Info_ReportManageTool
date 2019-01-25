package com.example.demo.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final ProductService productService;


    public void exportReportToHtml(HttpServletResponse response, String inputFileName, JRBeanCollectionDataSource dataSource) throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + inputFileName +".jrxml");
        try {
            response.setContentType("text/html");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
            HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
            exporter.exportReport();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }


    public void exportReportToPdf(HttpServletResponse response, String inputFileName, JRBeanCollectionDataSource dataSource) throws Exception{
        log.info("****************generate PDF report****************");
        //TODO Jasper 엔진에 넘겨주는 방식을 따로 뺄 순 없는가..
        log.info("*****" + dataSource);
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + inputFileName +".jrxml");
        OutputStream outputStream = response.getOutputStream();
        try {
            log.info("***infomind*** Start Compiling!!!!");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            log.info("***infomind*** Done compiling!!! ...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    null, dataSource);
            if (jasperPrint != null) {
                byte[] pdfReport = JasperExportManager
                        .exportReportToPdf(jasperPrint);
                outputStream.write(pdfReport);
                outputStream.flush();
//                response.reset();
//                response.setContentType("application/pdf");
//                response.setHeader("Cache-Control", "no-store");
//                response.setHeader("Cache-Control", "private");
//                response.setHeader("Pragma", "no-store");
//                response.setContentLength(pdfReport.length);
//                response.getOutputStream().write(pdfReport);
//                response.getOutputStream().flush();
            }
        } catch (JRException | IOException e) {
            e.printStackTrace();
        } finally {
            log.info("******* inputStream Close *******");
            inputStream.close();
            log.info("******* Servlet Output Stream Close *******");
            outputStream.close();
        }
    }

    public static JRBeanCollectionDataSource getDataSource(Collection dataSource) {
        return new JRBeanCollectionDataSource(dataSource);
    }
}
