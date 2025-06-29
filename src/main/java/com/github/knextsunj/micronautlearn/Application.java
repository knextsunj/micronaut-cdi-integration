package com.github.knextsunj.micronautlearn;

import com.github.knextsunj.micronautlearn.service.TestService;
import com.github.knextsunj.micronautlearn.service.impl.TestServiceImpl;
import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
//       ApplicationContext context = Micronaut.run(Application.class, args);

//        context.getBeanDefinitions(TestService.class).forEach(beanDef ->
//                System.out.println("🧠 Found bean: " + beanDef.getBeanType())
//        );
//        context.close();
    }
}