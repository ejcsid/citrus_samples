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
public class SampleApiTestIT extends JUnit4CitrusTestRunner {
    @Autowired
    private HttpClient sutClient;

    @Test
    @CitrusTest
    public void testGet() {
        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .send()
                .get()
                .accept(MediaType.ALL_VALUE));

        http(httpActionBuilder -> httpActionBuilder
                .client(sutClient)
                .receive()
                .response(HttpStatus.OK)
                .messageType(MessageType.JSON)
                .payload("{\"id\":@ignore@,\"content\":\"Hello, World!\"}"));
        /*
                .namespace("xh", "http://www.w3.org/1999/xhtml")
                .xpath("//xh:h1", "TODO list")
                .payload("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\n" +
                        "\"org/w3/xhtml/xhtml1-transitional.dtd\">" +
                        "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                        "<head>@ignore@</head>" +
                        "<body>@ignore@</body>" +
                        "</html>"));
*/
    }

}
