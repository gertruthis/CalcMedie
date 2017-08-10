package com.example.calcmedie;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FinalAvg extends AppCompatActivity {
    NoteleElevului note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_avg);

        note = new NoteleElevului();

        final Button button1 = (Button) findViewById(R.id.button5);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFinalResult();
            }
        });
    }

    private void showAlert(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()) {
                    new AlertDialog.Builder(FinalAvg.this)
                            .setTitle("Nota incorecta!")
                            .setMessage("Adauga ambele note!")
                            .setCancelable(false)
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).show();
                }
            }
        });
    }

    private boolean bothFieldsAreFilled(EditText edt, EditText edt1){
        return !(edt.getText().length() == 0 || edt1.getText().length() ==0);
    }

    private void showFinalResult(){
        EditText edt = (EditText) findViewById(R.id.editText3);
        EditText edt1 = (EditText) findViewById(R.id.editText4);

        if (bothFieldsAreFilled(edt, edt1)){
            note.note.add(new Nota(Float.parseFloat(edt.getText().toString())));
            note.note.add(new Nota(Float.parseFloat(edt1.getText().toString())));

            Intent i = new Intent(this, ResultShower.class);
            i.putExtra("vegso_jegy", Float.toString(new semAvgCalculator(note).calculateAVG()));
            startActivity(i);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }else{
            showAlert();
        }
    }

}
