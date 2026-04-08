package com.demo.jvm.spring.core.di.setter;

public class HelloService {
    private HelloDao helloDao;

    public HelloDao getHelloDao() {
        return helloDao;
    }

    public void setHelloDao(HelloDao helloDao) {
        this.helloDao = helloDao;
    }
}
