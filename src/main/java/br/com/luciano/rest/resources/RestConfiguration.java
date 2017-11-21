package br.com.luciano.rest.resources;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/service/")
public class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        packages(false, "br.com.luciano.rest.resources");
    }
}
