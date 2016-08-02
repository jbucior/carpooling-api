package com.carazem.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by RENT on 2016-07-18.
 */
@Component
public class ConfigService {

    @Autowired
    private Environment environment;

    public String get(String key) {
        return environment.getProperty(key);
    }

    public Integer getAsInt(String key) {
        return Integer.parseInt(get(key));
    }
}
