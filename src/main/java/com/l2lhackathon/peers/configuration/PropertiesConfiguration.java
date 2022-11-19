package com.l2lhackathon.peers.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties.d/application.properties")
@PropertySource(
        value = "classpath:properties.d/${spring.profiles.active}/application-${spring.profiles.active}.properties",
        ignoreResourceNotFound = true
)
public class PropertiesConfiguration {
}
