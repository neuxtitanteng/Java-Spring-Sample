package com.neux.spring;

import com.neux.spring.component.DefaultComponent;
import com.neux.spring.component.SampleComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfiguration {

    @Bean
    public SampleComponent defaultComponent() {
        return new DefaultComponent();
    }

    @Bean
    @Scope(value = "prototype")
    public SampleComponent prototypeComponent() {
        return new DefaultComponent();
    }

}
