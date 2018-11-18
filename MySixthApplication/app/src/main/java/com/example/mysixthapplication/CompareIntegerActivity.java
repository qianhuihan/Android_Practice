package com.example.mysixthapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysixthapplication.server.CompareService;

public class CompareIntegerActivity extends Activity {

    private static int maxInt;
    public static Handler handler = new Handler();
    private static TextView maxText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Bundle mybundle = new Bundle();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_integers);

        Button btn = findViewById(R.id.compare);
        final TextView first = findViewById(R.id.firstInt);
        final TextView second = findViewById(R.id.secondInt);

        maxText = findViewById(R.id.maxInt);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompareIntegerActivity.this,CompareService.class);
                mybundle.putString("first", first.getText().toString());
                mybundle.putString("second",second.getText().toString());
                intent.putExtras(mybundle);
                startService(intent);
            }
        });
    }

    public static void UpdateGUI(int maxNum){
        maxInt = maxNum;
        handler.post(RefreshLable);
    }
    private static Runnable RefreshLable = new Runnable() {
        @Override
        public void run() {
            maxText.setText(String.valueOf(maxInt));
        }
    };
}
