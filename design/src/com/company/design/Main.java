package com.company.design;

import com.company.design.adapter.*;

public class Main {
    public static void main(String[] args) {

//        AClazz aClazz = new AClazz();
//        BClazz bClazz = new BClazz();
//
//        SocketClient aClient = aClazz.getSocketClient();
//        SocketClient bClient = bClazz.getSocketClient();
//
//        System.out.println("두개의 객체가 동일한가?");
//        System.out.println(aClient.equals(bClient));    // true

        HairDryer hairDryer = new HairDryer();
        connect(hairDryer);

        Cleaner cleaner = new Cleaner();

        Electronic110V adapter = new SocketAdapter(cleaner);
        connect(adapter);

        AirConditional airConditional = new AirConditional();
        Electronic110V airAdapter = new SocketAdapter(airConditional);
        connect(airAdapter);
    }

    // 콘센트
    public static void connect(Electronic110V electronic110V) {
        electronic110V.powerOn();
    }
}
