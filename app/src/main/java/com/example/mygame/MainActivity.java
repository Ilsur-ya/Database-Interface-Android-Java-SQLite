package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.mygame.db.MyDbConstants;
import com.example.mygame.db.MyDbHelper;
import com.example.mygame.db.MyDbManager;

public class MainActivity extends AppCompatActivity {
    private MyDbManager myDbManager;
    private EditText editHP, editMoney, editPower;
    private TextView TestDbView;
    private TextView LevelTextView, HPTextView, StrengthTextView, MoneyTextView;
    private TextView ExperienceTextView;
    private ProgressBar HPProgressbar, ExpProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbManager = new MyDbManager(this);
        editHP = findViewById(R.id.editHP);
        editMoney = findViewById(R.id.editMoney);
        editPower = findViewById(R.id.editPower);
        TestDbView = findViewById(R.id.TestDbView);


        //{MyLevel, MyExp, -MyMaxExp-, MyHP, -MyMaxHP-, MyMoney, MyStrength}
        ExperienceTextView = findViewById(R.id.ExperienceTextView);
        LevelTextView = findViewById(R.id.LevelTextView);
        HPTextView = findViewById(R.id.HPTextView);
        MoneyTextView = findViewById(R.id.MoneyTextView);
        StrengthTextView = findViewById(R.id.StrengthTextView);

        HPProgressbar = findViewById(R.id.HPProgressBar);
        ExpProgressbar = findViewById(R.id.ExpProgressBar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //MyDbManager.updateDb("Default",
         //       1,100,100,
        //        0, 100,
        //        100, 1 );
        updateStatsInterface();

//        TestDbView.append(power);
//        TestDbView.append("\n");
//        StrengthTextView.setText(power);


    }

    public void updateStatsInterface() { //в интерфейс вставляются статы из бд
        myDbManager.openDb();
        String[] MyStats = myDbManager.getAllFromDb();
        //{MyLevel, MyExp, MyMaxExp, MyHP, MyMaxHP, MyMoney, MyStrength}
        LevelTextView.setText(MyStats[0]);

        ExperienceTextView.setText(MyStats[1] + "/" + Integer.parseInt(MyStats[2]));
        ExpProgressbar.setProgress(Integer.parseInt(MyStats[1]));
        ExpProgressbar.setMax(Integer.parseInt(MyStats[2]));
        HPTextView.setText(MyStats[3] + "/" + MyStats[4]);
        HPProgressbar.setProgress(Integer.parseInt(MyStats[3]));
        HPProgressbar.setMax(Integer.parseInt(MyStats[4]));
        MoneyTextView.setText(MyStats[5]);
        StrengthTextView.setText(MyStats[6]);
    }

    public void CheckMaxStats() {

    }

    public void onClickSave(View view) {
        TestDbView.setText("");
        myDbManager.updateDb("Default",
                1,Integer.parseInt(editHP.getText().toString()),100,
                0, 100,
                Integer.parseInt(editMoney.getText().toString()),
                Integer.parseInt(editPower.getText().toString()) );

        updateStatsInterface();

    }

    public void onClickSearch(View view) {

        myDbManager.updateDbSearch(-15, 20,10);
        MyDbManager.CheckMaxStats();

        updateStatsInterface();

    }








    public void onClickDrop(View view) {

        MyDbManager.updateDb("Default",
                1,100,100,
                0, 100,
                100, 1 );
        updateStatsInterface();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();

    }
}