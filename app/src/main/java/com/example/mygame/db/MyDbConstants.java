package com.example.mygame.db;

public class MyDbConstants {
    public static final String DB_NAME = "Game.db";
    public static final String TABLE_NAME = "main_table";
    public static final String _ID = "_id";
    public static final String TITLE = "Title";
    public static final String MONEY = "Money";
    public static final String HP = "HP";
    public static final String POWER = "Power";
    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, " + TITLE + " TEXT, " + HP + " TEXT, " + POWER + " TEXT, " + MONEY + " TEXT)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}
