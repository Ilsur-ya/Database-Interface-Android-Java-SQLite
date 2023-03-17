package com.example.mygame.db;

public class MyDbConstants {
    public static final String DB_NAME = "Game.db";
    public static final String TABLE_NAME = "main_table";
    public static final String _ID = "_id";
    public static final String NICK = "Nick";
    public static final String MONEY = "Money";
    public static final String HP = "HP";
    public static final String MAXHP = "MaxHP";
    public static final String POWER = "Power";
    public static final String EXP = "Exp"; //опыт
    public static final String MAXEXP = "MaxExp"; //опыт
    public static final String LEVEL = "Level"; //уровень

    public static final String ENEMYNAME = "EnemyName"; //название врага
    public static final String ENEMYLEVEL = "EnemyLevel";
    public static final String ENEMYPOWER = "EnemyPower";

    public static final int DB_VERSION = 1;
    public static final String TABLE_STRUCTURE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY, "
            + NICK + " TEXT, "
            + HP + " INTEGER, "
            + MAXHP + " INTEGER, "
            + POWER + " INTEGER, "
            + LEVEL + " INTEGER, "
            + EXP + " INTEGER, "
            + MAXEXP + " INTEGER, "
            + MONEY + " INTEGER)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
}
