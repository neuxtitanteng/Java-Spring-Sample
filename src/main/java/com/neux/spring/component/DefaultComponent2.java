package com.neux.spring.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "prototype")
public class DefaultComponent2 implements SampleComponent{
    private String name = null;

    public String getName() {
        return "DefaultComponent2="+name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
