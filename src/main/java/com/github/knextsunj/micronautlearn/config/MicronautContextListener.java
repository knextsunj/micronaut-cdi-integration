package com.github.knextsunj.micronautlearn.config;

import com.github.knextsunj.micronautlearn.service.TestService;
import com.github.knextsunj.micronautlearn.util.ApplicationContextBuilderHelper;
import io.micronaut.context.ApplicationContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import static com.github.knextsunj.micronautlearn.constants.MicronautLearnConstants.MICRONAUT_CONTEXT;

@WebListener
public class MicronautContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext = ApplicationContextBuilderHelper.getApplicationContext();
        TestService testService = applicationContext.getBean(TestService.class);
        if(testService==null) {
            System.out.println("TestService in context init is null");
        }
        sce.getServletContext().setAttribute(MICRONAUT_CONTEXT, applicationContext);
        System.out.println("Micronaut context stored in servlet context.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ApplicationContext ctx = (ApplicationContext)
                sce.getServletContext().getAttribute(MICRONAUT_CONTEXT);
        if (ctx != null) ctx.close();
    }
}