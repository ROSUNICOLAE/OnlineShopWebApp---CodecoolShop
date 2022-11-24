package com.codecool.shop.manager;

public class DatabaseManager {

    private static Boolean instance = null;

    private DatabaseManager(){}

    public static boolean isInMemory(){
        if(instance == null){
            instance = false;
        }
        return instance;
    }

    public static void switchBetweenDb_InMem(){
        instance = !instance;
    }
}
