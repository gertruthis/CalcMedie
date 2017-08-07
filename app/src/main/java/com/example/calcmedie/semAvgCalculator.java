package com.example.calcmedie;

/**
 * Created by user on 2017.07.22..
 */

public class semAvgCalculator {

    NoteleElevului note;

    semAvgCalculator(){note = new NoteleElevului();};
    semAvgCalculator(NoteleElevului n){
        note = new NoteleElevului();
        note = n;


    }

    public float calculateAVG(){
        if(note.teza.getNota() != 0) {
            return AvgT();
        }
        else{
            return Avg();
        }
    }

    private float Avg(){
        float sum = 0;
        for(Nota n: note.note){
            sum += n.getNota();
        }
        int a = note.note.size();
        return (sum/note.note.size());
    }

    private float AvgT(){
        float sum = 0;
        for(Nota n: note.note){
            sum += n.getNota();
        }
        return(((sum/note.note.size())*3+note.teza.getNota())/4);
    }
}
