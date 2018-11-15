package com.example.mythirdapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        TextView textView = (TextView)findViewById(R.id.welcome);
        String callerName = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        if(callerName!=null)
        {
            textView.setText("欢迎你，亲爱的" + callerName);
        }
    }
}
