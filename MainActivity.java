package com.example.myapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_id;
    private EditText et_pwd;
    private Button btn_login;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void setListener() {
        btn_login.setOnClickListener(this);
    }


    private void initView(){
        et_id = findViewById(R.id.et_id);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.bt_login);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                String id = et_id.toString();
                String pwd = et_pwd.toString();
                try {
                    URL url = new URL("http://www.prince-tech.club:8080/login");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.addRequestProperty("id",id);
                    connection.addRequestProperty("password",pwd);
                    connection.connect();
                    int code = connection.getResponseCode();
                    switch (code){
                        case 200:
                            Toast.makeText(this,connection.getResponseMessage(), Toast.LENGTH_LONG).show();
                    }


                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

        }
    }
}