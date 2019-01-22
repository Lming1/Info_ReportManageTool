package com.example.demo.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportService {
    private final ProductService productService;

    public void exportReportToHtml(HttpServletResponse response, String inputFileName) throws Exception {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productService.report());
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





    public void exportReportToPdf(HttpServletResponse response, String inputFileName) throws Exception{
        log.info("****************generate PDF report****************");
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("ReportTitle", "Product");
//        parameters.put("Author", "Infomind");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + inputFileName +".jrxml");
        try {
            log.info("***infomind*** Start Compiling!!!!");
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            log.info("***infomind*** Done compiling!!! ...");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                    null, dataSource);
            if (jasperPrint != null) {
                byte[] pdfReport = JasperExportManager
                        .exportReportToPdf(jasperPrint);
                response.reset();
                response.setContentType("application/pdf");
                response.setHeader("Cache-Control", "no-store");
                response.setHeader("Cache-Control", "private");
                response.setHeader("Pragma", "no-store");
                response.setContentLength(pdfReport.length);
                response.getOutputStream().write(pdfReport);
                response.getOutputStream().flush();
            }
        } catch (JRException | IOException e) {
            e.printStackTrace();
        } finally {
            log.info("******* inputStream Close *******");
            inputStream.close();
            log.info("******* Servlet Output Stream Close *******");
            response.getOutputStream().close();
        }
    }
}
