package com.github.knextsunj.micronautlearn.service.impl;

import com.github.knextsunj.micronautlearn.service.TestService;
import jakarta.inject.Singleton;

@Singleton
public class TestServiceImpl implements TestService {

    @Override
    public String message() {
        return "Hi from Micronaut+CDI";
    }
}
