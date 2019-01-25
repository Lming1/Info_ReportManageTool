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

/** TODO 하단 오류 해결할 것..
 * context: ROOT
 *   delegate: false
 * ----------> Parent Classloader:
 * java.net.URLClassLoader@26f0a63f
 * =com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl@7fb12d92}]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
 * 24-Jan-2019 16:59:21.926 ?? [localhost-startStop-2] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [ROOT] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@7f74f706]) and a value of type [java.lang.Class] (value [class SampleReport_1548316747836_191755]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
 * 24-Jan-2019 16:59:21.937 ?? [main] org.apache.coyote.AbstractProtocol.stop Stopping ProtocolHandler ["http-nio-8080"]
 * 24-Jan-2019 16:59:21.940 ?? [main] org.apache.coyote.AbstractProtocol.stop Stopping ProtocolHandler ["ajp-nio-8009"]
 * 24-Jan-2019 16:59:21.941 ?? [main] org.apache.coyote.AbstractProtocol.destroy Destroying ProtocolHandler ["http-nio-8080"]
 * 24-Jan-2019 16:59:21.942 ?? [main] org.apache.coyote.AbstractProtocol.destroy Destroying ProtocolHandler ["ajp-nio-8009"]
 */
public class ReportService {
    private final ProductService productService;


    public void exportReportToHtml(HttpServletResponse response, String inputFileName, JRBeanCollectionDataSource dataSource) throws Exception {
        try {
            response.setContentType("text/html");
            JasperReport jasperReport = compileReport(inputFileName);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
            HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
            exporter.exportReport();
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
    }


    public void exportReportToPdf(HttpServletResponse response, String inputFileName, JRBeanCollectionDataSource dataSource) throws Exception{
        log.info("****************generate PDF report****************");
        //TODO Jasper 엔진에 넘겨주는 방식을 따로 뺄 순 없는가..
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
    }

    public static JRBeanCollectionDataSource getDataSource(Collection dataSource) {
        return new JRBeanCollectionDataSource(dataSource);
    }

    public JasperReport compileReport(String inputFileName) throws Exception {
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/" + inputFileName +".jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            return jasperReport;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("******* InputStream Close *******");
            inputStream.close();
        }
        return null;
    }
}
