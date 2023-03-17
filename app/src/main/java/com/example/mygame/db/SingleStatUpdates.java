package com.example.mygame.db;

import android.content.ContentValues;

public class SingleStatUpdates {
    public static void updateHP(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.HP, value);
        MyDbManager.cursorForUpdate(cv);

    }
    public static void updateMaxHP(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.MAXHP, value);
        MyDbManager.cursorForUpdate(cv);

    }

    public static void updateExp(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.EXP, value);
        MyDbManager.cursorForUpdate(cv);

    }
    public static void updateMaxExp(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.MAXEXP, value);
        MyDbManager.cursorForUpdate(cv);

    }
    public static void updateLevel(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.LEVEL, value);
        MyDbManager.cursorForUpdate(cv);

    }
    public static void updateMoney(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.MONEY, value);
        MyDbManager.cursorForUpdate(cv);

    }
    public static void updateStrength(int value){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.POWER, value);
        MyDbManager.cursorForUpdate(cv);

    }
}
