package com.github.knextsunj.micronautlearn.config;

import com.github.knextsunj.micronautlearn.controller.TestController;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class LearnApp extends Application {

//    @Override
//    public Set<Class<?>> getClasses() {
////        Set<Class<?>> classes = new HashSet<>();
////        classes.add(TestController.class);
////        System.out.println("🔍 Registering resource: " + TestController.class.getName());
////        return classes;
//        return Collections.emptySet();
//    }

    @PostConstruct
    public void logResources() {
        System.out.println("✅ RestApp initialized. Registered classes:");
        getClasses().forEach(c -> System.out.println("🔹 " + c.getName()));
    }

    public LearnApp() {
        System.out.println("✅ RestApp instantiated by RESTEasy.");
    }
}


