package com.company.design;

import com.company.design.adapter.Electronic110V;
import com.company.design.strategy.*;

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

//        AtomicLong start = new AtomicLong();
//        AtomicLong end = new AtomicLong();
//
//        IBrowser aopBrowser = new AopBrowser("www.naver.com", () -> {
//            System.out.println("before");
//            start.set(System.currentTimeMillis());
//        }, () -> {
//            long now = System.currentTimeMillis();
//            end.set(now - start.get());
//        });
//        aopBrowser.show();
//        System.out.println("loading time : " + end.get());
//
//        aopBrowser.show();
//        System.out.println("loading time : " + end.get());

//        ** Decorator Design Example **
//        ICar audi = new Audi(1000);
//        audi.showPrice();
//
//        // a3
//        ICar audi3 = new A3(audi, "A3");
//        audi3.showPrice();
//        // a4
//        ICar audi4 = new A4(audi, "A4");
//        audi4.showPrice();
//        // a5
//        ICar audi5 = new A5(audi, "A5");
//        audi5.showPrice();

//        ** Observer Design Example **
//        Button button = new Button("버튼");
//        button.addListener(new IButtonListener() {
//            @Override
//            public void clickEvent(String event) {
//                System.out.println(event);
//            }
//        });
//        button.click("메시지 전달 : Click 1");
//        button.click("메시지 전달 : Click 2");
//        button.click("메시지 전달 : Click 3");
//        button.click("메시지 전달 : Click 4");

//        ** Facade Design Example **
//        Ftp ftpClient = new Ftp("www.foo.co.kr", 22, "/home/etc");
//        ftpClient.connect();
//        ftpClient.moveDirectory();
//
//        Writer writer = new Writer("text.tmp");
//        writer.fileConnect();
//        writer.write();
//
//        Reader reader = new Reader("text.tmp");
//        reader.fileConnect();
//        reader.fileRead();
//
//        reader.fileDisconnect();
//        writer.fileDisconnect();
//        ftpClient.disConnect();
//
//        SftpClient sftpClient = new SftpClient("www.foo.co.kr", 22, "/home/etc", "text.tmp");
//
//        sftpClient.connect();
//        sftpClient.write();
//        sftpClient.read();
//        sftpClient.disConnect();

//        ** Strategy Design Example **
        Encoder encoder = new Encoder();

        // base64
        EncodingStrategy base64 = new Base64Strategy();

        // normal
        EncodingStrategy normal = new NormalStrategy();

        String message = "hello java";

        encoder.setEncodingStrategy(base64);
        String base64Result = encoder.getMessage(message);
        System.out.println(base64Result);

        encoder.setEncodingStrategy(normal);
        String normalResult = encoder.getMessage(message);
        System.out.println(normalResult);

        encoder.setEncodingStrategy(new AppendStrategy());
        String appendResult = encoder.getMessage(message);
        System.out.println(appendResult);
    }


    // 콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
