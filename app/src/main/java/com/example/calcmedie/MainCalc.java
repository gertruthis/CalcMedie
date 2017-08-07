package com.example.calcmedie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainCalc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calc);
    }

    public void startAvgSemester(View view){
        Intent intent = new Intent(this, AvgSemester.class);
        startActivity(intent);
    }

    public void startFinalAvg(View view){
        Intent intent = new Intent(this, FinalAvg.class);
        startActivity(intent);
    }
}
