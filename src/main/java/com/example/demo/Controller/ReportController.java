package com.example.demo.Controller;


import com.example.demo.Service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report")
@Slf4j
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public String report() {
        return "report";
    }

    @GetMapping("/viewer")
    public void reportToHtml(HttpServletResponse response) throws Exception {
        reportService.exportReportToHtml(response, "SampleReport");
    }

    @GetMapping("/pdf")
    public void reportToPdf(HttpServletResponse response, HttpServletRequest request) throws Exception {
        reportService.exportReportToPdf(response, "SampleReport");
    }

}
