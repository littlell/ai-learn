package com.demo.jvm.spring.core.ioc.instancefactory;

public class HelloService {
    private Hello01Bean hello01Bean = new Hello01Bean();
    private Hello02Bean hello02Bean = new Hello02Bean();

    public Hello01Bean createNewHello01BeanInstance() {
        return hello01Bean;
    }

    public Hello02Bean createNewHello02BeanInstance() {
        return hello02Bean;
    }
}
