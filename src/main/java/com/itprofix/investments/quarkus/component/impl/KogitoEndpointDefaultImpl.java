package com.itprofix.investments.quarkus.component.impl;

import com.itprofix.investments.quarkus.component.KogitoComponent;
import com.itprofix.investments.quarkus.component.KogitoEndpoint;
import com.itprofix.investments.quarkus.component.KogitoProducerFactory;
import com.itprofix.investments.quarkus.component.UriUtils;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.support.DefaultEndpoint;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class KogitoEndpointDefaultImpl extends DefaultEndpoint implements KogitoEndpoint {

    Logger log = LoggerFactory.getLogger(getClass());

    private KogitoComponent component;
    private Map<String, Object> parameters;
    private final UriUtils.ParsedUri uri;

    public KogitoEndpointDefaultImpl(String uri, UriUtils.ParsedUri parsedUri, KogitoComponent component,
                                     Map<String, Object> parameters) throws Exception {
        super(uri, component);
        this.uri = parsedUri;
        this.component = component;
        this.parameters = parameters;
        log.info("Endpoint created class {} uri {}", this, this.getEndpointUri());
    }
    @Override
    public Producer createProducer() throws Exception {
        return KogitoProducerFactory.createProducer(this, this.uri, this.parameters);
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        return null;
    }

    public boolean isSingleton() {
        return true;
    }

    @Override
    public boolean isLenientProperties() {
        return true;
    }

}
