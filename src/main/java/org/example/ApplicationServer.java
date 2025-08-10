package org.example;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class ApplicationServer extends ResourceConfig {
    public ApplicationServer () {
        packages("org.example");
    }
}