package com.example.steamkey.configurations;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ErrorMvcAutoConfiguration.class)
public class CustomErrorConfiguration {
}
