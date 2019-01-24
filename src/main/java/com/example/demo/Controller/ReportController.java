package com.example.demo.Controller;


import com.example.demo.Service.ProductService;
import com.example.demo.Service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.Service.ReportService.getDataSource;

@Controller
@RequestMapping("/report")
@Slf4j
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;
    private final ProductService productService;
    private JRBeanCollectionDataSource dataSource;



    @GetMapping
    public String report() {
        return "report";
    }

    @GetMapping("/viewer")
    public void reportToHtml(HttpServletResponse response) throws Exception {
        dataSource = getDataSource(productService.productFindAll());
        reportService.exportReportToHtml(response, "SampleReport", dataSource);
    }

    @GetMapping("/pdf")
    public void reportToPdf(HttpServletResponse response) throws Exception {
        dataSource = getDataSource(productService.productFindAll());
        reportService.exportReportToPdf(response, "SampleReport", dataSource);
    }

}
