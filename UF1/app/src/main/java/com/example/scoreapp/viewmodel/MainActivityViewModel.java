package com.example.scoreapp.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scoreapp.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityViewModel  extends AndroidViewModel {

    public static final String SP_VISITOR = "SP_VISITOR";
    public static final String SP_LOCAL = "SP_LOCAL";
    private static final String SP_TIME = "SP_TIME";

    //int localScore, visitorScore;
    MutableLiveData<Integer> localScoreLD;
    MutableLiveData<Integer> visitorScoreLD;
    MutableLiveData<Double> timeLD;

    public MainActivityViewModel(Application a){
        super(a);

        localScoreLD = new MutableLiveData<>();
        visitorScoreLD = new MutableLiveData<>();
        timeLD = new MutableLiveData<>();
        //localScore=visitorScore=0;
        localScoreLD.setValue(0);
        visitorScoreLD.setValue(0);
        timeLD.setValue(0.0);
        SharedPreferences sharedpreferences =getPreferences();
        visitorScoreLD.setValue( sharedpreferences.getInt(SP_VISITOR,0));
        localScoreLD.setValue( sharedpreferences.getInt(SP_LOCAL, 0));
        timeLD.setValue( (double)sharedpreferences.getFloat(SP_TIME, 0.0f));
        //--------------------------------------------------------
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                double t = timeLD.getValue();
                timeLD.postValue(t+1);

                SharedPreferences sharedpreferences =getPreferences();
                SharedPreferences.Editor editor =  sharedpreferences.edit();
                editor.putFloat(SP_TIME, timeLD.getValue().floatValue());
                editor.commit();

            }
        },0,1000);
    }

    //---------------------------------------------
    public LiveData<Integer> getLocalScore() {
        return localScoreLD;
    }

    public LiveData<Integer> getVisitorScore() {
        return visitorScoreLD;
    }

    public LiveData<Double> getTime() {
        return timeLD;
    }


    public void incLocal(int i) {
        if( this.localScoreLD.getValue()+i>=0) {
            this.localScoreLD.setValue(this.localScoreLD.getValue() +  i);
        }
        saveChanges();
    }

    public void incVisitor(int i) {
        if( this.visitorScoreLD.getValue()+i>=0) {
            this.visitorScoreLD.setValue(this.visitorScoreLD.getValue() +  i);
        }
        saveChanges();
    }

    private void saveChanges() {

        SharedPreferences sharedpreferences =getPreferences();

        SharedPreferences.Editor editor =  sharedpreferences.edit();
        editor.putInt(SP_VISITOR, visitorScoreLD.getValue());
        editor.putInt(SP_LOCAL, localScoreLD.getValue());
        editor.commit();
    }


    private SharedPreferences getPreferences() {
        return getApplication().getSharedPreferences("SCORE", Context.MODE_PRIVATE);
    }
}
