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
public class QuoteTestIT extends JUnit4CitrusTestRunner {
    @Autowired
    private HttpClient sutClient;

    @Test
    @CitrusTest
    public void testQuote() {
        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .send()
                .get("/quote")
                .accept(MediaType.ALL_VALUE));

        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .receive()
                .response(HttpStatus.OK)
            );
//                .messageType(MessageType.PLAINTEXT)
//                .payload("{\"id\":@variable('id')@,\"content\":\"Hello, World!\"}"));

    }

}
