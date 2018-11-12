package com.example.mysecondapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MyPreferenceActivity extends PreferenceActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasHeaders()) {
            Button button = new Button(this);
            button.setText("Exit");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(MyPreferenceActivity.this)
                            .setTitle("提示")
                            .setMessage("确认退出吗？")
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                }
                            })
                            .create();
                    dialog.show();
                }
            });
            setListFooter(button);
        }
    }

    @Override
    protected boolean isValidFragment(String fragmentName) {
        System.out.println(fragmentName);
        return true;
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_home, target);
    }

    public static class MyFirstPrefFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        private EditTextPreference name;
        private ListPreference sex;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference_frag1);


            name = (EditTextPreference) getPreferenceScreen().findPreference("name");

            sex = (ListPreference) getPreferenceScreen().findPreference("sex");
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("name")) {
                InitNameSummary(name);
            } else if(key.equals("sex")) {
                InitSexSummary(sex);
            }
        }

        public void InitNameSummary(EditTextPreference nameEdit)
        {
            if(nameEdit == null || nameEdit.getText() == null || nameEdit.getText().equals("")) {
                nameEdit.setSummary("请输入您的名字");
            }
            else{
                nameEdit.setSummary(nameEdit.getText());
            }
        }

        public void InitSexSummary(ListPreference sexBox)
        {
            if(sexBox == null || sexBox.getEntry() == null || sexBox.getEntry().equals("")) {
                sexBox.setSummary("请输入您的性别");
            }
            else{
                sexBox.setSummary(sexBox.getEntry());
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            InitNameSummary(name);
            InitSexSummary(sex);
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onPause() {
            getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
            super.onPause();
        }
    }

}

