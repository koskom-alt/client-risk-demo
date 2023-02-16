package com.itprofix.investments.quarkus;

import org.apache.camel.Processor;
import org.kie.kogito.KogitoEngine;
import org.kie.kogito.Model;
import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class ProcessorFactory {
    Logger logger = LoggerFactory.getLogger(ProcessorFactory.class);
    @Inject
    AppRoot appRoot;

    @Inject
    Instance<KogitoEngine> runtime;

    @Inject
    @Named("testprocess")
    Process<? extends Model> process;



    public Processor testProcessor () {
        return exchange -> {
            Map<String, Object> map = new HashMap<>();
            Request request = (Request) exchange.getMessage().getBody();
            map.put("request", request);
            Model model = process.createModel();
            model.fromMap(map);
            ProcessInstance<?> processInstance = process.createInstance("bk2", model);

            logger.info("createdInstance: {}", processInstance.variables());
            processInstance.start();
        };
    }
    public Processor testMessageProcessor () {
        return exchange -> {
            Map<String, Object> map = new HashMap<>();
            Request request = (Request) exchange.getMessage().getBody();
            map.put("request", request);
            Model model = process.createModel();
            model.fromMap(map);

            ProcessInstance<?> processInstance = process.createInstance("bk2", model);


            logger.info("createdInstance: {}", processInstance.variables());
            processInstance.start();
        };
    }
}
