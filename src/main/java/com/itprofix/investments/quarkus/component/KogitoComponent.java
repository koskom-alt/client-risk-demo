package com.itprofix.investments.quarkus.component;

import com.itprofix.investments.quarkus.component.impl.KogitoEndpointDefaultImpl;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.Endpoint;
import org.apache.camel.support.DefaultComponent;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
@ApplicationScoped
@RegisterForReflection
public class KogitoComponent extends DefaultComponent {


    public KogitoComponent() {
        super();
    }

    @Named("kogito")
    KogitoComponent initComponent () {
        return this;
    }

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        final UriUtils.ParsedUri parsedUri = new UriUtils.ParsedUri(remaining);
        return new KogitoEndpointDefaultImpl(uri, parsedUri, this, parameters);
    }
}
