package com.example.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class info extends AppCompatActivity{
    TextView tvView, tvDate;
    Button btnName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tvView = (TextView) findViewById(R.id.textView);
        tvDate = (TextView) findViewById(R.id.tvDate);
        // получаем Intent, который вызывал это Activity
        Intent intent = getIntent();
// читаем из него action
        String action = intent.getAction();

        String format = "", textInfo = "";

// в зависимости от action заполняем переменные
        if (action.equals("android.example.intent.showtime")) {
            format = "HH:mm:ss";

            textInfo = "Time: ";
        }
        if (action.equals("android.example.intent.showdate")) {
            format = "dd.MM.yyyy";
            textInfo = "Date: ";
        }

// в зависимости от содержимого переменной format
// получаем дату или время в переменную datetime
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));
        String lName = intent.getStringExtra("lname");
        tvView.setText("Your name is: " + lName);
        tvDate.setText(textInfo + datetime);

    }

}