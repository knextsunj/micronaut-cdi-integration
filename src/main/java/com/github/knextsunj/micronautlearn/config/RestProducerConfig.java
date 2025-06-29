package com.github.knextsunj.micronautlearn.config;

import com.github.knextsunj.micronautlearn.service.TestService;
import com.github.knextsunj.micronautlearn.service.impl.TestServiceImpl;
import io.micronaut.context.ApplicationContext;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

import static com.github.knextsunj.micronautlearn.constants.MicronautLearnConstants.MICRONAUT_CONTEXT;

@ApplicationScoped
public class RestProducerConfig {
    @Inject
    private ServletContext servletContext;


//    static {
//        System.out.println("Invoked RestProducerConfig class");
//
//    }

    public RestProducerConfig() {
        System.out.println("Invoked RestProducerConfig");
    }

    @Produces
    @ApplicationScoped
    public TestService testService() {
        System.out.println("🧪 Producing TestService for CDI");
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute(MICRONAUT_CONTEXT);
        TestService testService = applicationContext.getBean(TestService.class);
        if(testService==null)
            System.out.println("testService configured is null");
        else
            System.out.println("testService configured is NOT NULL");
        return testService;
    }


}
