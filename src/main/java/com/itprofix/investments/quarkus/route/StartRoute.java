package com.itprofix.investments.quarkus.route;


import com.itprofix.investments.quarkus.ProcessorFactory;
import com.itprofix.investments.quarkus.Request;
import org.apache.camel.BeanInject;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.model.dataformat.JsonLibrary;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.kie.kogito.process.ProcessConfig;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StartRoute extends RouteBuilder {

    @Inject
    ProcessorFactory processorFactory;

    Processor startProcessor;
    Processor eventProcessor;

    @PostConstruct
    public void init ()
    {
        startProcessor = processorFactory.testProcessor();
        eventProcessor = processorFactory.testMessageProcessor();
    }

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .log(LoggingLevel.ERROR, "ERROR: ${headers}\n${body}\n${exception.stacktrace}")
        ;

        from("kafka:test_req")
                .routeId("CAMEL.REQ")
                .unmarshal().json(Request.class)
                .log(LoggingLevel.INFO, "incoming message ${body}")
                .process(startProcessor)
                //.to("kogito:start")
        ;

        from("kafka:errors")
                .routeId("CAMEL.REQ.EVENT")
                .unmarshal().json(Request.class)
                .log(LoggingLevel.INFO, "incoming message ${body}")
                .process(eventProcessor)
        //.to("kogito:start")
        ;
    }
}
