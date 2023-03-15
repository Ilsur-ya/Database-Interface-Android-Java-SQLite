package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mygame.db.MyDbConstants;
import com.example.mygame.db.MyDbHelper;
import com.example.mygame.db.MyDbManager;

public class MainActivity extends AppCompatActivity {
    private MyDbManager myDbManager;
    private EditText editHP, editMoney, editPower;
    private TextView TestDbView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbManager = new MyDbManager(this);
        editHP = findViewById(R.id.editHP);
        editMoney = findViewById(R.id.editMoney);
        editPower = findViewById(R.id.editPower);
        TestDbView = findViewById(R.id.TestDbView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        myDbManager.openDb();
        for(String hp : myDbManager.getFromDb()){
            TestDbView.append(hp);
            TestDbView.append("\n");

        }
    }

    public void onClickSave(View view) {
        TestDbView.setText("");
        myDbManager.insertToDb(editHP.getText().toString(), editMoney.getText().toString(), editPower.getText().toString());
        for(String hp : myDbManager.getFromDb()){

            TestDbView.append(hp);
            TestDbView.append("\n");


        }
    }
    public void onClickDrop(View view) {
        TestDbView.setText("");

        for(String hp : myDbManager.getFromDb()){

            TestDbView.append(hp);
            TestDbView.append("\n");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();

    }
}