package com.example.myfifthapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowAllStudentsActivity extends Activity{
    private static final String DB_TABLE = "student";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_stu);

        studentDiaplay();
    }

    public void studentDiaplay(){

        TableLayout showall = findViewById(R.id.show_all_table);
        SQLiteDatabase db = this.openOrCreateDatabase("student.db",MODE_PRIVATE,null);
        Cursor cursor = db.rawQuery("select * from " + DB_TABLE, null);

        for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()) {
            String stuID = cursor.getString(cursor.getColumnIndex("stuID"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String birthDate = cursor.getString(cursor.getColumnIndex("birthDate"));
            String phoneNumber = cursor.getString(cursor.getColumnIndex("phoneNumber"));

            TableRow stuRow = new TableRow(this);
            TextView id = new TextView(this);
            id.setText(stuID);
            TextView nam = new TextView(this);
            nam.setText(name);
            TextView birthday = new TextView(this);
            birthday.setText(birthDate);
            TextView phone = new TextView(this);
            phone.setText(phoneNumber);
            stuRow.addView(id);
            stuRow.addView(nam);
            stuRow.addView(birthday);
            stuRow.addView(phone);

            showall.addView(stuRow);
        }
    }
}
