package de.muenchen.citrus.samples.apitest;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EndpointConfig {

    @Bean
    public HttpClient sutClient() {
        return CitrusEndpoints
                .http()
                .client()
                .requestUrl("http://localhost:8082")
                .build();
    }

    @Bean
    public HttpServer httpQuoteServer() {
        return CitrusEndpoints.http().server()
                .autoStart(true)
                .port(8090)
                .build();
    }
}