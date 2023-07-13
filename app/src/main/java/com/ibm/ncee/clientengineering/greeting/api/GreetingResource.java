package com.ibm.ncee.clientengineering.greeting.api;

import java.util.Arrays;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greetings")
public class GreetingResource {

    @Inject
    @ConfigProperty(name = "greetings", defaultValue = "(You did not specify any greetings.)")
    private String greetings;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String[] getGreetings() {
        String[] greetingsArray = greetings.split(";");
        System.out.println("Serving greetings: " + Arrays.toString(greetingsArray));
        return greetingsArray;
    }
}
