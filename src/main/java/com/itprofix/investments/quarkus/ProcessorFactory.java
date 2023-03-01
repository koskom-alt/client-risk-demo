package com.itprofix.investments.quarkus;

import org.apache.camel.Processor;
import org.kie.kogito.KogitoEngine;
import org.kie.kogito.Model;
import org.kie.kogito.correlation.CompositeCorrelation;
import org.kie.kogito.correlation.Correlation;
import org.kie.kogito.incubation.application.AppRoot;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Signal;
import org.kie.kogito.process.impl.Sig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ProcessorFactory {
    Logger logger = LoggerFactory.getLogger(ProcessorFactory.class);
    @Inject
    AppRoot appRoot;

    @Inject
    Instance<KogitoEngine> runtime;


    @Inject
    @Named("async")
    Process<? extends Model> asyncProcess;



    public Processor testProcessor () {
        return exchange -> {
            Map<String, Object> map = new HashMap<>();
            Request request = (Request) exchange.getMessage().getBody();
            map.put("request", request);
            Model model = asyncProcess.createModel();
            model.fromMap(map);
            asyncProcess.activate();
            ProcessInstance<?> processInstance = asyncProcess.createInstance(UUID.randomUUID().toString(), model);


            logger.info("createdInstance: {}", processInstance.variables());
            processInstance.start();

        };
    }
    public Processor testMessageProcessor () {
        return exchange -> {
            Map<String, Object> map = new HashMap<>();
            Request request = (Request) exchange.getMessage().getBody();
            map.put("request", request);

            asyncProcess.instances().values().forEach(processInstance -> {
                processInstance.send(new Signal<String>() {
                    @Override
                    public String channel() {
                        return null;
                    }

                    @Override
                    public String payload() {
                        return null;
                    }

                    @Override
                    public String referenceId() {
                        return null;
                    }
                });
                logger.info("PI: bus_key {}, id: {}.", processInstance.businessKey(), processInstance.id());
            });

        };
    }
}
