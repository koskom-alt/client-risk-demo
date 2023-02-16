package com.itprofix.investments.quarkus.component;

import org.apache.camel.Exchange;
import org.apache.camel.support.DefaultProducer;

import java.util.Map;

public class KogitoProducer extends DefaultProducer {
    protected Map<String, Object> parameters;
    public KogitoProducer(KogitoEndpoint endpoint, Map<String, Object> parameters) {
        super(endpoint);
        this.parameters = parameters;
    }

    protected KogitoEndpoint getKogitoEndpoint() {
        return (KogitoEndpoint) getEndpoint();
    }

    @Override
    public void process(Exchange exchange) throws Exception {

    }

}
