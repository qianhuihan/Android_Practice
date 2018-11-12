package com.example.mysecondapplication;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

//LauncherActivity
public class MainActivity extends LauncherActivity {

    String[] name = {"进入用户设置（PreferenceActivity）","中国省份查看（ListActivity）","通讯录（ExpandableListActivity）"};
    Class<?>[] clazzs = {MyPreferenceActivity.class, MyListActivity.class,MyExpandableListActivity.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);
        setListAdapter(adapter);
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(MainActivity.this, clazzs[position]);
    }


//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }
//    public void onPreferenceClick(View view){
//        startActivity(new Intent("android.intent.action.SecondActivity"));
//    }
//    public void onListClick(View view){
//        startActivity(new Intent("android.intent.action.ThirdActivity"));
//    }
}
