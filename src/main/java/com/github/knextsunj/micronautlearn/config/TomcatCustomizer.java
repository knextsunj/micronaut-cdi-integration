package com.github.knextsunj.micronautlearn.config;

import com.github.knextsunj.micronautlearn.service.TestService;
import com.github.knextsunj.micronautlearn.util.ApplicationContextBuilderHelper;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.event.BeanCreatedEvent;
import io.micronaut.context.event.BeanCreatedEventListener;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Singleton;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextEnvironment;
import org.apache.webbeans.servlet.WebBeansConfigurationListener;

@Singleton
public class TomcatCustomizer implements BeanCreatedEventListener<Tomcat> {
    @Override
    public Tomcat onCreated(@NonNull BeanCreatedEvent<Tomcat> event) {
        Tomcat tomcat = event.getBean();

        tomcat.getHost().findChildren()[0].addLifecycleListener(lifecycleEvent -> {
            System.out.println("Current lifecycle is::: "+lifecycleEvent.getType());
            if ("configure_start".equals(lifecycleEvent.getType())) {


                ApplicationContext applicationContext = (ApplicationContext) event.getSource();
                TestService testService = applicationContext.getBean(TestService.class);
                if(testService==null)
                    System.out.println("Test service is null in context");
                else
                    System.out.println("TestService hashcode is:: "+testService.hashCode());
                ApplicationContextBuilderHelper.setApplicationContext(applicationContext);
                Context context = (Context) lifecycleEvent.getLifecycle();
                context.addApplicationListener(MicronautContextListener.class.getName());
                tomcat.enableNaming();
                tomcat.getServer().getGlobalNamingResources().addEnvironment(
                        new ContextEnvironment() {{
                            setName("BeanManager");
                            setType("jakarta.enterprise.inject.spi.BeanManager");
                            setValue(null);
                        }}
                );
                context.addApplicationListener(WebBeansConfigurationListener.class.getName());
                context.getServletContext().setAttribute("tomcat.context", context);
                context.addApplicationListener(RestEasyInitializer.class.getName());


//                Wrapper servlet = Tomcat.addServlet(context, "ResteasyServlet", new HttpServletDispatcher());
//                servlet.setLoadOnStartup(1);
//                context.addServletMappingDecoded("/api/*", "ResteasyServlet");
//
//                servlet.addInitParameter("javax.ws.rs.Application", "com.github.knextsunj.micronautlearn.config.LearnApp");
//
//                ServletContext servletContext = context.getServletContext();
//
//                ResteasyDeployment deployment = new ResteasyDeploymentImpl();
//                deployment.setApplication(new LearnApp());
//                CdiInjectorFactory cdiInjectorFactory = new CdiInjectorFactory();
//                deployment.setInjectorFactory(cdiInjectorFactory);
//
//                servletContext.setAttribute(ResteasyDeployment.class.getName(), deployment);
//
//                Wrapper wrapper = Tomcat.addServlet(context, "ResteasyServlet", new HttpServletDispatcher());
//                wrapper.setLoadOnStartup(1);
//                wrapper.addInitParameter("resteasy.servlet.mapping.prefix", "/api");
//                context.addServletMappingDecoded("/api/*", "ResteasyServlet");
            }

        });
        return tomcat;
    }
}
