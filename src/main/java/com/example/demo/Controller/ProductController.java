package com.example.demo.Controller;

import com.example.demo.Service.ProductService;
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
import java.io.IOException;
import java.io.InputStream;

@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
@Slf4j

//TODO 리펙토링(ProductController -> ReportService, Controller)
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String product() {
        return "product";
    }




    @GetMapping("/report")
    public void reportToHtml(HttpServletResponse response) throws Exception {


    }

    @GetMapping("/pdf")
    public void reportToPdf(HttpServletResponse response, HttpServletRequest request) throws Exception {
        log.info("****************generate PDF report****************");

    }

}
