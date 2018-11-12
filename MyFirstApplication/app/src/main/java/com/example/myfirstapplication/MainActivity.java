package com.example.myfirstapplication;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity {

    private int status = 0;
    GestureDetector gestureDetector = null;
    private ViewSwitcher viewSwitcher;
    private int count = -1;
    int [] images = new int[]{
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4
    };
    private LayoutInflater inflater;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TabHost头部菜单
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.mytab1, tabHost.getTabContentView(), true);
        LayoutInflater.from(this).inflate(R.layout.mytab2, tabHost.getTabContentView(), true);
        LayoutInflater.from(this).inflate(R.layout.mytab3, tabHost.getTabContentView(), true);
        LayoutInflater.from(this).inflate(R.layout.mytab4, tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec("TAB1")
                .setIndicator("登录信息").setContent(R.id.layout01));
        tabHost.addTab(tabHost.newTabSpec("TAB2")
                .setIndicator("个人信息").setContent(R.id.layout02));
        tabHost.addTab(tabHost.newTabSpec("TAB3")
                .setIndicator("地址信息").setContent(R.id.layout03));
        tabHost.addTab(tabHost.newTabSpec("TAB4")
                .setIndicator("注册进度").setContent(R.id.layout04));

        //图片按钮
        ImageButton lastButton = (ImageButton)findViewById(R.id.lastStepButton);
        ImageButton nextButton = (ImageButton)findViewById(R.id.nextStepButton);
        lastButton.setImageResource(R.drawable.laststep);
        nextButton.setImageResource(R.drawable.nextstep);

        //图片滑动
        inflater = LayoutInflater.from(this);
        viewSwitcher = (ViewSwitcher)findViewById(R.id.photoSwitcher);
        viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                return inflater.inflate(R.layout.picture_view,null);
            }
        });
        next(null);
        gestureDetector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (velocityX > 0)
                    prev(null);
                if (velocityX < 0)
                    next(null);
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        //下拉框
        Spinner spinner = (Spinner) findViewById(R.id.bloodType);
        final List<String> list = new ArrayList<String>();
        list.add("A型");
        list.add("B型");
        list.add("O型");
        list.add("AB型");
        //一个ArrayAdapter的数组适配器，数组适配器能够将界面控件和底层数据绑定在一起
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        //android.R.layout.simple_spinner_dropdown_item是Android系统内置的一种浮动菜单
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //下拉List
        ListView listView = (ListView) findViewById(R.id.provinceView);
        final List<String> list2 = new ArrayList<String>();
        list2.add("北京");
        list2.add("天津");
        list2.add("黑龙江");
        list2.add("吉林");
        list2.add("辽宁");
        list2.add("河北");
        list2.add("山东");
        list2.add("江苏");
        list2.add("上海");
        list2.add("浙江");
        list2.add("福建");
        list2.add("广东");
        list2.add("江西");
        list2.add("安徽");
        list2.add("河南");
        list2.add("山西");
        list2.add("内蒙古");
        list2.add("宁夏");
        list2.add("甘肃");
        list2.add("陕西");
        list2.add("湖北");
        list2.add("湖南");
        list2.add("广西");
        list2.add("云南");
        list2.add("贵州");
        list2.add("四川");
        list2.add("重庆");
        list2.add("青海");
        list2.add("新疆");
        list2.add("西藏");
        list2.add("海南");
        list2.add("香港");
        list2.add("澳门");
        list2.add("台湾");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list2);
        listView.setAdapter(adapter2);
        final TextView textView = (TextView) findViewById(R.id.provinceText);
        AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                String msg = list2.get(arg2);
                ;
                textView.setText(msg);
            }
        };
        listView.setOnItemClickListener(listViewListener);

        //进度条
        final ProgressBar mybar = findViewById(R.id.myProgressBar);
        Button registBtn = findViewById(R.id.register);

        registBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    public void run() {
                        while (status < 100) {
                            status = doWork();
                            mybar.setProgress(status);
                        }
                    }
                }.start();
            }
        });

        //对话框
        final TextView address = (TextView) findViewById(R.id.address);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addressDialog = new AlertDialog.Builder(MainActivity.this);
                final View addressView = LayoutInflater.from(MainActivity.this).inflate(R.layout.address_dialog, null);
                addressDialog.setTitle("请输入具体家庭地址：");
                addressDialog.setView(addressView);

                addressDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                addressDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText addressText = (EditText)addressView.findViewById(R.id.addressText);
                        String content = addressText.getText().toString();
                        TextView address = (TextView) findViewById(R.id.address);
                        address.setText(content);
                    }
                });
                addressDialog.show();
            }
        });

    }

    public void next(View view){
        if(count>=images.length-1)
            count=-1;
        viewSwitcher.setInAnimation(this,R.anim.slide_in_right);
        viewSwitcher.setOutAnimation(this,R.anim.slide_out_left);
        ImageView img = viewSwitcher.getNextView().findViewById(R.id.slideImg);
        img.setImageResource(images[++count]);
        viewSwitcher.showNext();
    }
    public void prev(View view){
        if(count<1)
            count = images.length;
        viewSwitcher.setInAnimation(this,android.R.anim.slide_in_left);
        viewSwitcher.setOutAnimation(this,android.R.anim.slide_out_right);
        ImageView img = viewSwitcher.getNextView().findViewById(R.id.slideImg);
        img.setImageResource(images[--count]);
        viewSwitcher.showPrevious();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private int doWork() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        status++;
        return status;
    }
}
