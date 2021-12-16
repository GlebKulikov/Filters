package com.example.intentfilter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AlignActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align);
        findViewById(R.id.left).setOnClickListener(this);
        findViewById(R.id.center).setOnClickListener(this);
        findViewById(R.id.right).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.left:
                intent.putExtra("align", "left");
                break;
            case R.id.center:
                intent.putExtra("align", "center");
                break;
            case R.id.right:
                intent.putExtra("align", "right");
                break;
            default:
                break;
        }
        setResult(2, intent);
        finish();
    }
}