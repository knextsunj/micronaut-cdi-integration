package com.github.knextsunj.micronautlearn.config;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import org.apache.catalina.Context;
import org.jboss.resteasy.cdi.CdiInjectorFactory;
import org.jboss.resteasy.core.ResteasyDeploymentImpl;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.spi.ResteasyDeployment;

import java.util.Set;

public class RestEasyInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {

        System.out.println("🛡️ ResteasyInitializer triggered");

        ResteasyDeployment deployment = new ResteasyDeploymentImpl();
        deployment.setApplication(new LearnApp());
        deployment.setInjectorFactory(new CdiInjectorFactory()); // CDI has initialized now

        ctx.setAttribute(ResteasyDeployment.class.getName(), deployment);

        ServletRegistration.Dynamic reg = ctx.addServlet("ResteasyServlet", new HttpServletDispatcher());
        reg.setLoadOnStartup(1);
        reg.addMapping("/api/*");
        reg.setInitParameter("resteasy.servlet.mapping.prefix", "/api");
        Context tomcatContext = (Context) ctx.getAttribute("tomcat.context");
        tomcatContext.addServletMappingDecoded("/api/*", "ResteasyServlet");

    }

}
