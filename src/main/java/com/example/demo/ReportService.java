package com.example.demo;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    private JasperReport jasperReport;

    public JRDataSource getDataSource(List<Map<String, Object>> result) {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        return dataSource;
    }

    public JasperReport getReport(String path) throws JRException {
        if (jasperReport == null)
        {
            InputStream inputStream = this.getClass().getResourceAsStream(path);
            jasperReport = JasperCompileManager.compileReport(inputStream);
        }
        return jasperReport;
    }

    //TODO ProductService, ProductController 리펙토링 진행하여 반영할 것.

//    public void reportExport(List<Map<String, Object>> result, HttpServletResponse response) throws Exception{
//        response.setContentType("text/html;charset=UTF-8");
//        JRDataSource dataSource = getDataSource(result);
//        JasperReport report = getReport("/reports/SampleReport.jrxml");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, dataSource);
//        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//        exporter.exportReport();
//
//    }
}
