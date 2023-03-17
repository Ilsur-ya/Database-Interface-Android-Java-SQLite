package com.example.mygame.db;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.fragment.app.FragmentManager;

import java.util.Arrays;

//крч будет удобно
public class MyDbManager {
    public static Object dropDb;
    private Context context;
    private static MyDbHelper myDbHelper;
    private static SQLiteDatabase db;

    public MyDbManager(Context context) { //конструктор
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public static void openDb(){
        db = myDbHelper.getWritableDatabase();

    }

    public void dropDb(){
        myDbHelper.onUpgrade(db, 1, MyDbConstants.DB_VERSION);
    }

    public void closeDb(){
        myDbHelper.close();
    }

    public void insertToDb(int hp, int money, int power){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.HP, hp);
        cv.put(MyDbConstants.MONEY, money);
        cv.put(MyDbConstants.POWER, power);

        db.insert(MyDbConstants.TABLE_NAME, null, cv);

    }

    public static void updateDb(String Nick, int Level, int HP, int MaxHP, int Exp, int MaxExp, int Money, int Strength){
        ContentValues cv = new ContentValues();

        cv.put(MyDbConstants.NICK, Nick);
        cv.put(MyDbConstants.LEVEL, Level);
        cv.put(MyDbConstants.HP, HP);
        cv.put(MyDbConstants.MAXHP, MaxHP);
        cv.put(MyDbConstants.EXP, Exp);
        cv.put(MyDbConstants.MAXEXP, MaxExp);
        cv.put(MyDbConstants.MONEY, Money);
        cv.put(MyDbConstants.POWER, Strength);



        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, MyDbConstants._ID);
        cursor.moveToLast();
        String idLast = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants._ID));

        String selection = "_id = ?";
        String[] selectionArgs = { idLast };

        int count = db.update(MyDbConstants.TABLE_NAME,
                cv,
                selection,
                selectionArgs);
        cursor.close();
    }


    public static void updateDbSearch(int HP, int Exp, int Money) {

        String[] OldStats = getAllFromDb(); //узнаем старые значения чтобы из них вычесть
        //MyLevel, MyExp, MyMaxExp, MyHP, MyMaxHP, MyMoney, MyStrength
        Integer OldHP = Integer.parseInt(OldStats[3]);
        Integer OldExp = Integer.parseInt(OldStats[1]);
        Integer OldMoney = Integer.parseInt(OldStats[5]);

        ContentValues cv = new ContentValues();

        cv.put(MyDbConstants.HP, HP+OldHP);
        cv.put(MyDbConstants.EXP, Exp+OldExp);
        cv.put(MyDbConstants.MONEY, Money+OldMoney);

        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, MyDbConstants._ID);
        cursor.moveToLast();
        String idLast = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants._ID));

        String selection = "_id = ?";
        String[] selectionArgs = { idLast };

        int count = db.update(MyDbConstants.TABLE_NAME,
                cv,
                selection,
                selectionArgs);

        cursor.close();
    }


    public String getFromDb(){
        String lastPower = new String();

        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, MyDbConstants._ID);
        cursor.moveToLast();
        Integer power = cursor.getInt(cursor.getColumnIndexOrThrow(MyDbConstants.MONEY));
        lastPower = "7";
        //while (cursor.moveToPrevious()) {
        //    String power = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.POWER));
        //    lastPower = power;
        //}


        cursor.close();
        return lastPower;
    }

    public static String[] getAllFromDb(){
        String MyLevel = new String();
        String MyExp = new String();
        String MyMaxExp = new String();
        String MyHP = new String();
        String MyMaxHP = new String();
        String MyMoney = new String();
        String MyStrength = new String();


        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, MyDbConstants._ID);
        cursor.moveToLast();

        MyLevel = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.LEVEL));
        MyExp = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.EXP));
        MyHP = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.HP));
        MyMaxExp = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.MAXEXP));
        MyMaxHP = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.MAXHP));
        MyMoney = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.MONEY));
        MyStrength = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.POWER));



        cursor.close();
        return new String[]{MyLevel, MyExp, MyMaxExp, MyHP, MyMaxHP, MyMoney, MyStrength};
    }

/*    public String getFromDb(){
        String lastPower = new String();

        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, MyDbConstants._ID);
        cursor.moveToLast();
        String power = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.POWER));
        lastPower = power;
        //while (cursor.moveToPrevious()) {
        //    String power = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.POWER));
        //    lastPower = power;
        //}


        cursor.close();
        return lastPower;
    }*/
    public static int[] ParseIntFromStringArray(String[] Array){
        int[] NewArray = Arrays.stream(Array).mapToInt(Integer::parseInt).toArray();
        return NewArray;
    }
    public static void CheckMaxStats(){
        String[] NewStats = getAllFromDb();
        int[] NewStatsInt = ParseIntFromStringArray(NewStats);
        //MyLevel, MyExp, MyMaxExp, MyHP, MyMaxHP, MyMoney, MyStrength
        if (NewStatsInt[1] > NewStatsInt[2]){ //Новый уровень!
            SingleStatUpdates.updateExp(0);
            SingleStatUpdates.updateLevel(NewStatsInt[0]+1);
            SingleStatUpdates.updateMaxExp( NewStatsInt[2]+1);

        }
        if (NewStatsInt[3] > NewStatsInt[4]){


        }
    }



    public static void cursorForUpdate(ContentValues cv){

        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, MyDbConstants._ID);
        cursor.moveToLast();
        String idLast = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants._ID));
        String selection = "_id = ?";
        String[] selectionArgs = { idLast };
        int count = db.update(MyDbConstants.TABLE_NAME,
                cv,
                selection,
                selectionArgs);
        cursor.close();
    }
}
