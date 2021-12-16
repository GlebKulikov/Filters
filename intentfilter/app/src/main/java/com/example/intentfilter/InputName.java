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

public class InputName extends AppCompatActivity implements View.OnClickListener{
    EditText etName;
    Button btnName;
    TextView textOldName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_name);
        etName = (EditText) findViewById(R.id.tvName);
        textOldName = (TextView) findViewById(R.id.textOldName);
        btnName  = (Button) findViewById(R.id.btnName);
        btnName.setOnClickListener(this);

        Intent intent = getIntent();
// читаем из него action
        String action = intent.getAction();

        String format = "", textInfo = "";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String datetime = sdf.format(new Date(System.currentTimeMillis()));
        String lName = intent.getStringExtra("lname");
        textOldName.setText("Your name is: " + lName);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("name", etName.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}