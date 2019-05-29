package de.muenchen.citrus.samples.apitest;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import com.consol.citrus.message.MessageType;
import org.apache.http.HttpVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.junit.Test;

@ContextConfiguration(classes = { EndpointConfig.class })
public class QuoteTestIT extends JUnit4CitrusTestRunner {
    @Autowired
    private HttpClient sutClient;

    @Autowired
    private HttpServer quoteServer;

    @Test
    @CitrusTest
    public void testQuote() {

        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .send()
                .get("/quote")
                .accept(MediaType.ALL_VALUE));

        receive(receiveMessageBuilder -> receiveMessageBuilder
            .endpoint(quoteServer));

        send(sendMessageBuilder -> sendMessageBuilder
                .endpoint(quoteServer)
                .payload("{\"type\":\"success\",\"value\":{\"id\":2,\"quote\":\"With Boot you deploy everywhere you can find a JVM basically.\"}}"));

        http(httpActionBuilder -> httpActionBuilder
                .server(quoteServer)
                .receive()
                .get());

/*        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .receive()
                .response(HttpStatus.OK)
            );

        http(httpActionBuilder -> httpActionBuilder
                .server(quoteServer)
                .send()
                .response(HttpStatus.OK)
                .payload("{\"type\":\"success\",\"value\":{\"id\":2,\"quote\":\"With Boot you deploy everywhere you can find a JVM basically.\"}}")
                .version(HttpVersion.HTTP_1_1.toString())
                .contentType("application/json;charset=UTF-8"));
*/
    }

}
