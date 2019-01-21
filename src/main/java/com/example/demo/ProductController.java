package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
@Slf4j
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String product() {
        return "product";
    }



    @GetMapping("/report")
    public void report(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/SampleReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();

    }

    @GetMapping("/pdf")
    public void reportToPdf(HttpServletResponse response, HttpServletRequest request) throws Exception {
        log.info("****************generate PDF report****************");

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("ReportTitle", "Product");
        parameters.put("Author", "Infomind");
        try {
            log.info("***infomind*** Start Compiling!!!!");
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(productService.report());
            InputStream inputStream = this.getClass().getResourceAsStream("/reports/SampleReport.jrxml");
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
                response.getOutputStream().close();
            }
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

}
