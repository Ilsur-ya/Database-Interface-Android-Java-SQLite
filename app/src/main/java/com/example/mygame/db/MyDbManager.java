package com.example.mygame.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

//крч будет удобно
public class MyDbManager {
    public static Object dropDb;
    private Context context;
    private MyDbHelper myDbHelper;
    private SQLiteDatabase db;

    public MyDbManager(Context context) { //конструктор
        this.context = context;
        myDbHelper = new MyDbHelper(context);
    }

    public void openDb(){
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

    public void updateDb(int hp, int money, int power){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.HP, hp);
        cv.put(MyDbConstants.MONEY, money);
        cv.put(MyDbConstants.POWER, power);

        int lastPower = Integer.parseInt(getFromDb());

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
    }
    public void dropTable(){

    }
    public String getFromDb(){
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
    }

}
