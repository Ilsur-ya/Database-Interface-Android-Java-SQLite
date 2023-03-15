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

    public void insertToDb(String hp, String money, String power){
        ContentValues cv = new ContentValues();
        cv.put(MyDbConstants.HP, hp);
        cv.put(MyDbConstants.MONEY, money);
        cv.put(MyDbConstants.POWER, power);

        db.insert(MyDbConstants.TABLE_NAME, null, cv);

    }
    public void dropTable(){

    }
    public List<String> getFromDb(){
        List<String> tempList = new ArrayList<>();

        Cursor cursor = db.query(MyDbConstants.TABLE_NAME, null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            String hp = cursor.getString(cursor.getColumnIndexOrThrow(MyDbConstants.HP));
            tempList.add(hp);
        }


        cursor.close();
        return tempList;
    }

}
