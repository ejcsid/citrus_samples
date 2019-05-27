package de.muenchen.citrus.samples.apitest;

import com.consol.citrus.dsl.runner.AbstractTestBehavior;

public class ApiTestBehavior extends AbstractTestBehavior {
    public void apply() {
        variable("apitest.baseurl", "http://localhost:8082");
        variable("apitest.greeting.url", "greeting");
        variable("apitest.quote.url", "quote");
    }
}
