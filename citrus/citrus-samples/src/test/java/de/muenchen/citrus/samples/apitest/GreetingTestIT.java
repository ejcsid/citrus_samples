package de.muenchen.citrus.samples.apitest;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;

@ContextConfiguration(classes = { EndpointConfig.class })
public class GreetingTestIT extends JUnit4CitrusTestRunner {
    @Autowired
    private HttpClient sutClient;

    @Test
    @CitrusTest
    public void testGreeting() {
        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .send()
                .get("/greeting")
                .accept(MediaType.ALL_VALUE));

        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.PLAINTEXT)
                .payload("{\"id\":@variable('id')@,\"content\":\"Hello, World!\"}"));
    }

    @Test
    @CitrusTest
    public void testGreetingWithName() {
        variable("name", "Eva");
        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .send()
                .get("/greeting?name=${name}")
                .accept(MediaType.ALL_VALUE));

        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.PLAINTEXT)
                .payload("{\"id\":@variable('id')@,\"content\":\"Hello, ${name}!\"}"));
    }

}
