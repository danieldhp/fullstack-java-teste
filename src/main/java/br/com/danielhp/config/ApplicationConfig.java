package br.com.danielhp.config;


import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Daniel on 17/01/2017.
 */
@ApplicationPath("testeContabilizeiService")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(ObjectMapperProvider.class);
        resources.add(JacksonFeature.class);
        resources.add(CORSResponseFilter.class);

        addRestResourceClasses(resources);

        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.danielhp.api.ClienteApi.class);
        resources.add(br.com.danielhp.api.NotaFiscalApi.class);
        resources.add(br.com.danielhp.api.CalculoImpostosApi.class);
    }
}