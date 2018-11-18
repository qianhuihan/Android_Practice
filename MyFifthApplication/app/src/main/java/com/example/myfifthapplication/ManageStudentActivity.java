package com.example.myfifthapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ManageStudentActivity extends Activity{

    private static final String DB_TABLE = "student";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_student);

        Button search_btn = findViewById(R.id.search);
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStu();
            }
        });
    }

    public void selectStu(){
        SQLiteDatabase db = this.openOrCreateDatabase("student.db",MODE_PRIVATE,null);
        EditText id_text = findViewById(R.id.stuid);
        String stu_id = id_text.getText().toString();

        LinearLayout manageLayout = findViewById(R.id.manage_layout);
        if (!stu_id.equals("")) {
            Cursor cursor = db.rawQuery("select * from  student where stuID=?", new String[]{stu_id});//得到游标
            if (cursor.getCount() == 0) {
                Toast.makeText(ManageStudentActivity.this,"该学号不存在，请重新输入！",Toast.LENGTH_SHORT);
            } else {
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    final String stuID = cursor.getString(cursor.getColumnIndex("stuID"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String birthDate = cursor.getString(cursor.getColumnIndex("birthDate"));
                    String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));

                    final Student stu = new Student(stuID, name, birthDate, phoneNumber);
                    TextView stuTextView = new TextView(this);
                    stuTextView.setText("学号：" + stuID + "；姓名：" + name + "；生日：" + birthDate + "；联系方式：" + phoneNumber);

                    manageLayout.addView(stuTextView);

                    Button deleteBtn = new Button(this);
                    deleteBtn.setText("删除数据");
                    deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteStu(stuID);
                        }
                    });
                    manageLayout.addView(deleteBtn);

                    Button updateBtn = new Button(this);
                    updateBtn.setText("更新数据");
                    updateBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateStu(stu);
                        }
                    });
                    manageLayout.addView(updateBtn);
                }
            }
        }
        else {
            Toast.makeText(ManageStudentActivity.this,"学号不能为空！",Toast.LENGTH_SHORT);
        }
    }

    public void deleteStu (String stuID) {

        SQLiteDatabase db = this.openOrCreateDatabase("student.db",MODE_PRIVATE,null);
        try {
            db.delete(DB_TABLE, " _id" + "=" + stuID, null);
            Toast.makeText(ManageStudentActivity.this, "删除成功！", Toast.LENGTH_SHORT);
        } catch (Exception e) {
            Toast.makeText(ManageStudentActivity.this, "删除失败！", Toast.LENGTH_SHORT);
        }
    }
    public void updateStu (final Student student){

        final SQLiteDatabase db = this.openOrCreateDatabase("student.db",MODE_PRIVATE,null);
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(ManageStudentActivity.this);
        final View dialogView = LayoutInflater.from(ManageStudentActivity.this)
                .inflate(R.layout.update_dialog,null);
        customizeDialog.setTitle("修改学生信息");
        EditText id_edit_text = (EditText) dialogView.findViewById(R.id.update_stu_id);
        id_edit_text.setText(student.getStuID());
        final EditText name_edit_text = dialogView.findViewById(R.id.update_stu_name);
        name_edit_text.setText(student.getName());
        final EditText birth_edit_text = dialogView.findViewById(R.id.update_stu_birthDay);
        birth_edit_text.setText(student.getBirthDate());
        final EditText phone_edit_text = dialogView.findViewById(R.id.update_stu_phone);
        phone_edit_text.setText(student.getPhoneNumber());
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定更改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // 获取EditView中的输入内容
                        String name = name_edit_text.getText().toString();
                        String birthday = birth_edit_text.getText().toString();
                        String phone = phone_edit_text.getText().toString();

                        ContentValues updateValues = new ContentValues();
                        updateValues.put("name", name);
                        updateValues.put("birthDate", birthday);
                        updateValues.put("phoneNumber", phone);
                        try {
                            db.update(DB_TABLE, updateValues, "_id = " + student.getStuID(), null);
                            Toast.makeText(ManageStudentActivity.this, "更新成功！", Toast.LENGTH_SHORT);
                        } catch (Exception e){
                            Toast.makeText(ManageStudentActivity.this, "更新失败！", Toast.LENGTH_SHORT);
                        }

                    }
                });
        customizeDialog.show();
    }
}
