package com.flowergarden.run;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

public class Run {

    public static void main(String[] args) throws Exception {

        String webappDirLocation = "src/main/webapp";
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8080);

        StandardContext ctx = (StandardContext) tomcat.addWebapp(
                "",
                new File(webappDirLocation).getAbsolutePath()
        );

        ctx.setParentClassLoader(Run.class.getClassLoader());
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();

    }
}
