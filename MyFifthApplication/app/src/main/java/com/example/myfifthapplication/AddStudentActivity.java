package com.example.myfifthapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddStudentActivity extends Activity {
    private static final String DB_TABLE = "student";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        Button addbtn = findViewById(R.id.add_btn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id_text = (EditText)findViewById(R.id.stu_id);
                String stuid = id_text.getText().toString();
                EditText name_text = findViewById(R.id.stu_name);
                String stuname = name_text.getText().toString();
                if(stuid == null || stuid.equals("") || stuname == null || stuname.equals("")) {
                    Toast.makeText(AddStudentActivity.this, "学号和姓名不能为空",Toast.LENGTH_SHORT).show();
                }
                else {
                    EditText birth_text = findViewById(R.id.stu_birthDay);
                    String stubirthday = birth_text.getText().toString();
                    EditText phone_text = findViewById(R.id.stu_phone);
                    String stuphone = phone_text.getText().toString();
                    Student stu = new Student(stuid, stuname, stubirthday, stuphone);
                    try {
                        insert(stu);
                        Toast.makeText(AddStudentActivity.this, "添加成功！",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(AddStudentActivity.this, "添加失败，请重试！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public long insert(Student student) {
        ContentValues newValues = new ContentValues();
        SQLiteDatabase db = this.openOrCreateDatabase("student.db",MODE_PRIVATE,null);

        newValues.put("stuID", student.getStuID());
        newValues.put("name", student.getName());
        newValues.put("birthDate", student.getBirthDate());
        newValues.put("phoneNumber", student.getPhoneNumber());
        db.execSQL("create table if not exists " + DB_TABLE + " (_id integer " +
                " primary key autoincrement, " +
                " stuID varchar(20)," +
                " name varchar(20)," +
                " birthDate varchar(11)," +
                " phoneNumber varchar(11))");
        return db.insert(DB_TABLE, null, newValues);
    }

}
