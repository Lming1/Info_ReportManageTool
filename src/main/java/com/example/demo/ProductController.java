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
        log.info("infomind"+ String.valueOf(productService.report()));
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

    }

}
