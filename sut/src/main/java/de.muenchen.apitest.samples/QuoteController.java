package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@RestController
public class QuoteController {

    @Value("${quote.url}")
    private String quoteUrl;

    @RequestMapping("/quote")
    public Quote quote(RestTemplate restTemplate){
        Quote quote = restTemplate.getForObject(
                quoteUrl, Quote.class);
        return quote;
    }
}