package com.itprofix.investments.quarkus.component;


import org.apache.camel.Exchange;
import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.internal.process.runtime.KogitoProcessInstance;
import org.kie.kogito.process.management.ProcessInstanceManagementResource;
import org.kie.kogito.services.signal.DefaultSignalManagerHub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class StartProcessProducer extends KogitoProducer {


    @Inject
    AppRoot appRoot;

    @Inject
    DefaultSignalManagerHub defaultSignalManagerHub;
    Logger logger = LoggerFactory.getLogger(StartProcessProducer.class);


    public static final String EXCHANGE_HEADER_PROCESS_DEFINITION_KEY = "kogitoProcessDefinitionKey";
    public static final String REQUEST_HEADERS = "request_headers";
    public static final String REQUEST_PAYLOAD = "request_payload";

    public static final String PROCESS_DEFINITION_KEY_PARAMETER = "processDefinitionKey";
    public static final String COPY_MESSAGE_PROPERTIES_PARAMETER = "copyProperties";

    public static final String COPY_MESSAGE_HEADERS_PARAMETER = "copyHeaders";

    private final String processDefinitionKey;

    Map<String, Object> processVariables = new HashMap<>();
    public StartProcessProducer(KogitoEndpoint endpoint, Map<String, Object> parameters) {
        super(endpoint, parameters);
        if (parameters.containsKey(PROCESS_DEFINITION_KEY_PARAMETER)){
            this.processDefinitionKey = (String) parameters.get(PROCESS_DEFINITION_KEY_PARAMETER);
        } else {
            processDefinitionKey = null;
            //throw new IllegalArgumentException("You need to pass the '" + PROCESS_DEFINITION_KEY_PARAMETER + "' parameter! Parameters received: " + parameters);
        }
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        if (parameters.containsKey(COPY_MESSAGE_PROPERTIES_PARAMETER)) {
            processVariables = new HashMap<>(exchange.getProperties());
        }
        if (parameters.containsKey(COPY_MESSAGE_HEADERS_PARAMETER)) {
            processVariables.put(REQUEST_HEADERS, exchange.getMessage().getHeaders());
        }
        processVariables.put(REQUEST_PAYLOAD, exchange.getMessage().getBody());

        var processDefinitionKey = this.processDefinitionKey != null ? this.processDefinitionKey : exchange.getIn().getHeader(EXCHANGE_HEADER_PROCESS_DEFINITION_KEY, String.class);

        CDI.current().getBeanManager().getBeans(ProcessInstanceManagementResource.class, new AnnotationLiteral<Any>() {}).forEach(bean -> {
            logger.warn("BEAN NAME {} TYPE {}", bean.getName(), bean.getBeanClass());
        });



        KogitoProcessInstance instance = null;
        //var id = appRoot.get(ProcessIds.class).get("testProcess");


        logger.info("RESULT: {} || {}", appRoot, defaultSignalManagerHub);

        //instance = kogitoProcessRuntime.get().startProcess(processDefinitionKey, processVariables);


        exchange.setProperty(EXCHANGE_HEADER_PROCESS_DEFINITION_KEY, instance.getProcessId());
        exchange.setProperty("process_instance_id", instance.getRootProcessInstanceId());

    }
}
