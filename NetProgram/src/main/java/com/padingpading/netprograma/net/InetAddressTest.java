package com.padingpading.netprograma.net;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author libin
 * @description
 * @date 2022-04-06
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException {
        //通过域名查询ip定制
        InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
        System.out.println(inetAddress);
        //通过ip地址查询域名,插叙拿不到
        InetAddress inetAddress2 = InetAddress.getByName("180.97.34.96");
        System.out.println(inetAddress2);
        //多个ip地址
        InetAddress[] inetAddress3= InetAddress.getAllByName("www.baidu.com");
        for (InetAddress address : inetAddress3) {
//            www.baidu.com/180.97.34.96
//            www.baidu.com/180.97.34.94
            System.out.println(address);
        }
    }
}
