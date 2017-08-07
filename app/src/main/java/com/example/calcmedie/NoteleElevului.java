package com.example.calcmedie;

import java.util.ArrayList;

/**
 * Created by user on 2017.07.19..
 */

public class NoteleElevului {
    Teza teza;
    ArrayList<Nota> note;

    NoteleElevului(){
        teza = new Teza(0);
        note = new ArrayList<Nota>();
    }

    public void setPartialNote(float tz){
        teza.setNota(tz);
    }

    public float getPartialNote(){
        if(teza.getNota() != 0)
            return teza.getNota();
        return 0;
    }

    public void addNote(float n){
        note.add(new Nota(n));
    }

    public void removeLastAddedNote(){
        if(!note.isEmpty())
            note.remove(note.size() - 1);
    }

    public String getLastAddedNote(){
        return Float.toString(note.get(note.size()-1).getNota());
    }
}
