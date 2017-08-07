package com.example.calcmedie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ResultShower extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_shower);

        String s = getIntent().getStringExtra("vegso_jegy");
        EditText editText = (EditText) findViewById(R.id.editText5);
        editText.setText(s);
    }
}
