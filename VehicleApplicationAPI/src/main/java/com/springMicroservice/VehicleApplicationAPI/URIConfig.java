package com.springMicroservice.VehicleApplicationAPI;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class URIConfig implements WebMvcConfigurer {

    /**
     * {@inheritDoc}
     * <p>This implementation is empty.
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.
                        favorParameter(false).
                //ignore the accept headers
                        ignoreAcceptHeader(true).
                //dont use Java Activation Framework since we are manually specifying the mediatypes required below
                        useJaf(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("json", MediaType.APPLICATION_JSON);
    }
}
