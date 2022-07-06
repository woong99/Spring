package com.company.design;

import com.company.design.adapter.Electronic110V;
import com.company.design.aop.AopBrowser;
import com.company.design.proxy.IBrowser;

import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {

//        ** Singleton Design Example **
//        AClazz aClazz = new AClazz();
//        BClazz bClazz = new BClazz();
//
//        SocketClient aClient = aClazz.getSocketClient();
//        SocketClient bClient = bClazz.getSocketClient();
//
//        System.out.println("두개의 객체가 동일한가?");
//        System.out.println(aClient.equals(bClient));    // true

//        ** Adapter Design Example **
//        HairDryer hairDryer = new HairDryer();
//        connect(hairDryer);
//
//        Cleaner cleaner = new Cleaner();
//
//        Electronic110V adapter = new SocketAdapter(cleaner);
//        connect(adapter);
//
//        AirConditional airConditional = new AirConditional();
//        Electronic110V airAdapter = new SocketAdapter(airConditional);
//        connect(airAdapter);

//        ** Proxy Design Example **
//        Browser browser = new Browser("www.naver.com");
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();

//        IBrowser browser = new BrowserProxy("www.naver.com");
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();
//        browser.show();

        AtomicLong start = new AtomicLong();
        AtomicLong end = new AtomicLong();

        IBrowser aopBrowser = new AopBrowser("www.naver.com", () -> {
            System.out.println("before");
            start.set(System.currentTimeMillis());
        }, () -> {
            long now = System.currentTimeMillis();
            end.set(now - start.get());
        });
        aopBrowser.show();
        System.out.println("loading time : " + end.get());

        aopBrowser.show();
        System.out.println("loading time : " + end.get());
    }


    // 콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
