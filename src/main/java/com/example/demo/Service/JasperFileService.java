package com.example.demo.Service;


/**
 *
 * @author Raphael Lee. Infomind Co.
 *
 *
 */
public interface JasperFileService {
    void init();

    void deleteAll();

    boolean jrxmlFileExists(String file);

    String loadJrxmlFile(String file);
}
