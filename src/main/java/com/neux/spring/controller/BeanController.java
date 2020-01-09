package com.neux.spring.controller;

import com.neux.spring.component.DefaultComponent;
import com.neux.spring.component.SampleComponent;
import com.neux.spring.service.DateService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BeanController {

    @Autowired
    private DateService dateService;

    @Autowired
    private BeanFactory beanFactory;

//    @Autowired
//    private SampleComponent defaultComponent;

    @RequestMapping("/bean/service")
    public Date test1() {
        return dateService.getToday();
    }

    @RequestMapping("/bean/default")
    public String test2() {

        DefaultComponent defaultComponent = (DefaultComponent) beanFactory.getBean("defaultComponent");
        return defaultComponent.getName();
    }

    @RequestMapping("/bean/default/{name}")
    public String test3(@PathVariable("name") String name) {
        DefaultComponent defaultComponent = (DefaultComponent) beanFactory.getBean("defaultComponent");
        defaultComponent.setName(name);
        return defaultComponent.getName();
    }

    @RequestMapping("/bean/prototype")
    public String test4() {
        DefaultComponent prototypeComponent = (DefaultComponent) beanFactory.getBean("prototypeComponent");
        return prototypeComponent.getName();
    }

    @RequestMapping("/bean/prototype/{name}")
    public String test5(@PathVariable("name") String name) {
        DefaultComponent prototypeComponent = (DefaultComponent) beanFactory.getBean("prototypeComponent");
        prototypeComponent.setName(name);
        return prototypeComponent.getName();
    }

//    @RequestMapping("/bean/defaultByAutowired")
//    public String test6() {
//        return defaultComponent.getName();
//    }
//
//    @RequestMapping("/bean/defaultByAutowired/{name}")
//    public String test7(@PathVariable("name") String name) {
//        defaultComponent.setName(name);
//        return defaultComponent.getName();
//    }
}
