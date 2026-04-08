package com.demo.jvm.spring.core.ioc.instancefactory;

public class Hello02Bean {
    private static Hello02Bean helloBean = new Hello02Bean();

    public static Hello02Bean createInstance(){
        return helloBean;
    }
}
