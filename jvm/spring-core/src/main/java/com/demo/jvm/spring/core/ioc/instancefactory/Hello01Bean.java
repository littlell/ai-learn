package com.demo.jvm.spring.core.ioc.instancefactory;

public class Hello01Bean {
    private static Hello01Bean helloBean = new Hello01Bean();

    public static Hello01Bean createInstance(){
        return helloBean;
    }
}
