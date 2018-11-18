package com.example.mysixthapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    public static final String URL_PATH = "192.168.31.202";
    public static final int SOCKET_PORT = 50000;
    private EditText input, show;
    private Button sendBtn;
    private OutputStream os;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText) findViewById(R.id.main_et_input);
        show = (EditText) findViewById(R.id.main_et_show);
        sendBtn = (Button) findViewById(R.id.main_btn_send);

        Button compareBtn = findViewById(R.id.compareBtn);
        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CompareIntegerActivity.class);
                startActivity(intent);
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // 如果消息来自子线程
                if (msg.what == 0x234) {
                    // 将读取的内容追加显示在文本框中
                    show.append("\n" + msg.obj.toString());
                }
            }
        };
        new Thread() {
            public void run() {
                Socket socket;
                try {
                    socket = new Socket(URL_PATH, SOCKET_PORT);
                    // 客户端启动ClientThread线程不断读取来自服务器的数据
                    new Thread(new ClientThread(socket, handler)).start();
                    os = socket.getOutputStream();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    // 将用户在文本框内输入的内容写入网络
                    byte[] writeContent = (input.getText().toString() + "\r\n").getBytes();
                    os.write(writeContent);
                    // 清空input文本框数据
                    input.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
