package com.demo.jvm.spring.core.di.collections;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ComplexBean {
    private Properties properties;
    private Map map;
    private List list;
    private Set set;

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    @Override
    public String toString() {
        return "ComplexBean{" +
                "properties=" + properties +
                ", map=" + map +
                ", list=" + list +
                ", set=" + set +
                '}';
    }
}
