package com.itprofix.investments.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.kie.api.runtime.KieRuntime;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class IProcessImpl {


    Logger logger = LoggerFactory.getLogger(getClass());

    public Object process(KogitoProcessContext context) {

        logger.info("headers: {}", context.getHeaders());
        logger.info("context.getContextData(): {}", context.getContextData());
        logger.info("processId: {}", context.getProcessInstance().getProcessId());
        logger.info("procHeaders: {}", context.getProcessInstance().getHeaders());
        logger.info("variables: {}", context.getProcessInstance().getVariables());
        logger.info("meta: {}", context.getNodeInstance().getNode().getMetaData());
        logger.info("channels: {}", context.getKogitoProcessRuntime().getProcessEventManager().getProcessEventListeners());
        return "";
    }
    
}
