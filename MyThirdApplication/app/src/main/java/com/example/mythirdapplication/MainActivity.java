package com.example.mythirdapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button aboutBtn = (Button) findViewById(R.id.about);
        aboutBtn.setOnClickListener(this);
        Button telbtn = (Button) findViewById(R.id.tel);
        telbtn.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                Fragment eyecarefragment = new EyecareFragment();
                FragmentManager eyecarefragmentManager = getSupportFragmentManager();
                FragmentTransaction eyecarefragmentTransaction = eyecarefragmentManager. beginTransaction();
                eyecarefragmentTransaction.replace(R.id.right_layout, eyecarefragment);
                eyecarefragmentTransaction.commit();
                break;
            case R.id.button2:
                Fragment fragment = new RightFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager. beginTransaction();
                transaction.replace(R.id.right_layout, fragment);
                transaction.commit();
                break;
            case R.id.about:
                Toast.makeText(MainActivity.this, "进入查看Intent简介", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(MainActivity.this, AboutActivity.class);
                intent.putExtra(Intent.EXTRA_TEXT, MainActivity.this.getLocalClassName());
                startActivity(intent);
                break;
            case R.id.tel:
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:0577-63701234"));
                startActivity(intent2);
                break;
            case R.id.btn3:
                Intent intentThree = new Intent();
                intentThree.setAction("app.action.test");
                startActivity(intentThree);
                break;
            case R.id.btn4:
                Intent intentFour = new Intent();
                intentFour.setAction("app.action.test2");
                intentFour.addCategory("categoryTest");
                startActivity(intentFour);
                break;
            case R.id.btn5:
                Intent intentFive = new Intent();
                intentFive.setAction("app.action.test3");
                intentFive.setData(Uri.parse("content://com.example.mythirdapplication"));
                startActivity(intentFive);
                break;
            default:
                break;
        }
    }

}
