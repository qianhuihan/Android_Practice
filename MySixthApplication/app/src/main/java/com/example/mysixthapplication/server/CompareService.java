package com.example.mysixthapplication.server;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import com.example.mysixthapplication.CompareIntegerActivity;

public class CompareService extends Service {
    private Thread workThread;
    Bundle bundle = null;
    int first = 0, second = 0;
    public void onCreate(){
        super.onCreate();
        workThread = new Thread(null,backgroudWork,"WorkThread");
    }
    public void onStart(Intent intent, int startId){
        super.onStart(intent,startId);
        bundle = intent.getExtras();
        String f1 = bundle.getString("first");
        String s2 = bundle.getString("second");
        if(!f1.toString().equals("") && !s2.toString().equals("")){
            first = Integer.parseInt(f1);
            second = Integer.parseInt(s2);
        }
        if(!workThread.isAlive()){
            workThread.start();
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    private Runnable backgroudWork = new Runnable() {
        @Override
        public void run() {
            int randomDouble = IntCompare(first,second);
            CompareIntegerActivity.UpdateGUI(randomDouble);
            stopSelf();
        }
    };
    int IntCompare(int a, int b) {
        if(a>=b)
            return a;
        else
            return b;
    }
}
