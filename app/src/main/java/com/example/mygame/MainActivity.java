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
    private TextView StrengthTextView, MoneyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDbManager = new MyDbManager(this);
        editHP = findViewById(R.id.editHP);
        editMoney = findViewById(R.id.editMoney);
        editPower = findViewById(R.id.editPower);
        TestDbView = findViewById(R.id.TestDbView);
        StrengthTextView = findViewById(R.id.StrengthTextView);
        MoneyTextView = findViewById(R.id.MoneyTextView);

    }

    @Override
    protected void onResume() {
        super.onResume();

        myDbManager.openDb();
        String power = myDbManager.getFromDb();
        TestDbView.append(power);
        TestDbView.append("\n");
        StrengthTextView.setText(power);


    }

    public void onClickSave(View view) {
        TestDbView.setText("");
        myDbManager.insertToDb(Integer.parseInt(editHP.getText().toString()),
                Integer.parseInt(editMoney.getText().toString()),
                Integer.parseInt(editPower.getText().toString()));
        String hp = myDbManager.getFromDb();





    }

    public void onClickSearch(View view) {
        TestDbView.setText("");

        myDbManager.updateDb(Integer.parseInt(editHP.getText().toString()),
                Integer.parseInt(editMoney.getText().toString()),
                Integer.parseInt(editPower.getText().toString()));

        String power = myDbManager.getFromDb();

        StrengthTextView.setText(power);




    }
    public void onClickDrop(View view) {
        TestDbView.setText("");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();

    }
}