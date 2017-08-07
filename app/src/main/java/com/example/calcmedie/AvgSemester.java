package com.example.calcmedie;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

import static com.example.calcmedie.R.id.editText2;

public class AvgSemester extends AppCompatActivity {
    NoteleElevului note;

    AvgSemester(){
        note = new NoteleElevului();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avg_semester);

        Intent intent = getIntent();

        CheckBox cb = (CheckBox) findViewById(R.id.checkBox);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if(isChecked == true)
                    showTzFields();
                else
                    hideTzFields();
            }
        });

        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(NoteCanBeAdded(Float.parseFloat(editText.getText().toString()))) {
                        showAlert();
                        editText.setText("");
                    }else {

                        savePartialNote(editText.getText().toString());
                        disableEdittext(editText);
                    }
                }
                return false;
            }
        });


        final EditText editText2 = (EditText) findViewById(R.id.editText2);
        editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT) {
                    if(NoteCanBeAdded(Float.parseFloat(editText2.getText().toString()))) {
                        showAlert();
                        editText2.setText("");
                    }else {
                        addNewNote(editText2.getText().toString());
                        editText2.setText("");
                        showNoteFields();
                        addNewNoteToTextfield();
                    }
                }
                return false;
            }
        });

        final Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deleteLastAddedNote();
                removeLastNoteFromTextfield();
            }
        });

        final Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showFinalResult();
            }
        });
    }

    private void showFinalResult(){
        Intent i = new Intent(this, ResultShower.class);
        i.putExtra("vegso_jegy", Float.toString(new semAvgCalculator(note).calculateAVG()));
        startActivity(i);
    }

    private void deleteLastAddedNote(){
        note.removeLastAddedNote();
    }

    private void showAlert(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (!isFinishing()) {
                    new AlertDialog.Builder(AvgSemester.this)
                            .setTitle("Nota incorecta!")
                            .setMessage("Nota adaugata trebuie sa fie intre 1 si 10!")
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

    private boolean NoteCanBeAdded(float note){
        return (1 > note || note > 10);
    }

    private void addNewNoteToTextfield(){
        TextView tv = (TextView) findViewById(R.id.textView4);
        String str = tv.getText().toString();
        tv.setText("");
        str = str + note.getLastAddedNote() + ", ";
        tv.setText(str);
    }

    public void removeLastNoteFromTextfield(){
        TextView tv = (TextView) findViewById(R.id.textView4);
        String str = new String();
        tv.setText("");
        for(Nota n : note.note){
            str = str + Float.toString(n.getNota()) + ", ";
        }
        tv.setText(str);
    }

    private void showNoteFields(){
        TextView tv = (TextView) findViewById(R.id.textView2);
        TextView tv2 = (TextView) findViewById(R.id.textView4);
        Button b = (Button) findViewById(R.id.button4);
        tv.setVisibility(View.VISIBLE);
        tv2.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
    }

    public void showTzFields(){
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setVisibility(View.VISIBLE);

        EditText edt = (EditText) findViewById(R.id.editText);
        edt.setVisibility(View.VISIBLE);
        edt.requestFocus();
    }

    public void hideTzFields(){
        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setVisibility(View.INVISIBLE);

        EditText edt = (EditText) findViewById(R.id.editText);
        edt.setVisibility(View.INVISIBLE);
    }

    private void savePartialNote(String f){
        note.setPartialNote(Float.parseFloat(f));
    }

    private void addNewNote(String f){
        note.addNote(Float.parseFloat(f));
    }

    private void disableEdittext(EditText edt){
        edt.setActivated(false);
        edt.setFocusable(false);
    }
}
