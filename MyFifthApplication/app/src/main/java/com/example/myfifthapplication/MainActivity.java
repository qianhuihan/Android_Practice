package com.example.myfifthapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showAll = findViewById(R.id.show_all_students);
        showAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showStu = new Intent(MainActivity.this,ShowAllStudentsActivity.class);
                startActivity(showStu);
            }
        });

        Button manageStu = findViewById(R.id.stu_manage);
        manageStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent manageStudent = new Intent(MainActivity.this,ManageStudentActivity.class);
                startActivity(manageStudent);
            }
        });

        Button addStu = findViewById(R.id.add_stu);
        addStu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addStudent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivity(addStudent);
            }
        });
    }
}
