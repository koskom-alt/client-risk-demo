package com.itprofix.investments.quarkus.component;

import java.util.Map;

public class KogitoProducerFactory {
    private KogitoProducerFactory(){};

    public static KogitoProducer createProducer (final KogitoEndpoint endpoint, final UriUtils.ParsedUri uri, final Map<String, Object> parameters) {
        switch (uri.getType()) {
            case StartProcess:
                return new StartProcessProducer(endpoint, parameters);
            /*case SendMessage:
                return new MessageProducer(endpoint, parameters);*/
            default:
                throw new IllegalArgumentException("Cannot create a producer for URI '" + uri + "' - new ProducerType '"
                        + uri.getType() + "' not yet supported?");
        }
    }
}