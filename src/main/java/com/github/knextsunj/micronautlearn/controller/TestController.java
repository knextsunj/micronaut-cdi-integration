package com.github.knextsunj.micronautlearn.controller;

import com.github.knextsunj.micronautlearn.service.TestService;
import io.micronaut.context.ApplicationContext;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import static com.github.knextsunj.micronautlearn.constants.MicronautLearnConstants.MICRONAUT_CONTEXT;

@Path("/test")
@ApplicationScoped
public class TestController {

//    @Inject
//    private Instance<TestService> testServiceInstance;

    @Inject
    private TestService testService;

    @Inject
    private ServletContext servletContext;

//    @Inject
//    private ApplicationContext applicationContext;

    static {
        System.out.println("TestResource class loaded.");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        String str;
//        if(testServiceInstance !=null) {
//            return testServiceInstance.get().message();
//        }
        if(testService!=null) {
            return testService.message();
        }
        else {
            System.out.println("Test service is null");
//            ApplicationContext applicationContext = ApplicationContext.run();

            ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute(MICRONAUT_CONTEXT);
            TestService testService = applicationContext.getBean(TestService.class);
            str = testService.message();
            return str;
        }
//        return "hello "+str;

//        ApplicationContext ctx = ApplicationContext.run(); // Ideally, reuse this
//        ctx.getBeanDefinitions(TestService.class).forEach(beanDef ->
//                System.out.println("🧠 Found bean: " + beanDef.getBeanType())
//        );
//        return "Check console logs.";
    }

    @PostConstruct
    public void init() {
        System.out.println("🚀 TestController initialized! testService = " + testService);
    }


}
