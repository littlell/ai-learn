package com.demo.jvm.basics.apache.cxf;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.xml.bind.JAXBException;
import java.util.Map;

public class DynamicClient {
    public static void main(String[] args) throws JAXBException {
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://home.glodon.com:7783/Siebel2/GMall/ProxyServices/GLDZCMInterfaceProxy?wsdl");

        Map ctx = client.getRequestContext();

        ctx.put("ws-security.username", "gmall_sys");
        ctx.put("ws-security.password", "gmall@2#s$d%");
        Object[] result = new Object[0];
        try {
            result = client.invoke("GetAssetInfo", "KEVIN");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(result[0]);
    }
}
