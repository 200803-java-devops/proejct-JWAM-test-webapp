package com.project2;

import java.io.File;
import java.util.Optional;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * Robotap the app for developers
 */
public class App {
    public static final Optional<String> port = Optional.ofNullable(System.getenv("PORT"));

    public static void main(String[] args) {
        Tomcat server = new Tomcat();
        server.setBaseDir(new File("target/tomcat/").getAbsolutePath());
        server.setPort(Integer.valueOf(port.orElse("8080")));
        server.getConnector();
        server.addWebapp("", new File("src/main/java/com/project2/resources").getAbsolutePath());
        try {
            server.start();
        } catch (LifecycleException e) {
            System.err.println("trouble starting tomcat: " + e);
        }
        server.getServer().await();
    }
}
