package com.example.starter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.user")
@Data
public class UserProperties {
    private String name;
}
