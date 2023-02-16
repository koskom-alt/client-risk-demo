package com.itprofix.investments.quarkus.bean;


import io.quarkus.arc.DefaultBean;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.drools.kiesession.session.StatefulKnowledgeSessionImpl;
import org.jbpm.process.instance.InternalProcessRuntime;
import org.jbpm.process.instance.KogitoProcessRuntimeImpl;
import org.jbpm.process.instance.ProcessRuntimeFactoryServiceImpl;
import org.jbpm.process.instance.ProcessRuntimeImpl;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;
import org.kie.kogito.quarkus.conf.KogitoBuildTimeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
public class BeanConfiguration {
    Logger logger = LoggerFactory.getLogger(BeanConfiguration.class);

    @Produces
    @DefaultBean
    public BeanFinder beanFinder() {

        return new BeanFinder();
    }

    public class BeanFinder {
        public BeanFinder () {

        }
    }
}
