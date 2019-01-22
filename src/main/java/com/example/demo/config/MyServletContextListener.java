package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//TODO ProductController Memory leak 해결할 것....
//Error msg
/**
----------> Parent Classloader:
        java.net.URLClassLoader@26f0a63f
        =com.sun.org.apache.xerces.internal.util.XMLGrammarPoolImpl@31de944a}]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
        21-Jan-2019 17:56:48.743 ?? [localhost-startStop-2] org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks The web application [ROOT] created a ThreadLocal with key of type [java.lang.ThreadLocal] (value [java.lang.ThreadLocal@415c304f]) and a value of type [java.lang.Class] (value [class SampleReport_1548060977448_109698]) but failed to remove it when the web application was stopped. Threads are going to be renewed over time to try and avoid a probable memory leak.
**/
@WebListener
@Slf4j
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("*************** Context Init ***************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("*************** Context Destroy ***************");
    }
}
