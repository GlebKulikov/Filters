package com.example.intentfilter;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_OpenCite, button_Time, button_Date, button_Camera, button_SetName, button_Image;
    EditText url_link, editText_Name;
    ImageView imageView;
    LinearLayout linearLayout;
    TextView text_example, textview_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_Time = (Button) findViewById(R.id.button_Time);
        button_Date = (Button) findViewById(R.id.button_Date);
        linearLayout = findViewById(R.id.linear_Layout);
        text_example = (TextView) findViewById(R.id.text_example);
        button_SetName = (Button) findViewById(R.id.button_SetName);
        button_OpenCite = (Button) findViewById(R.id.button_OpenCite);
        button_Image= (Button) findViewById(R.id.button_Image);
        editText_Name = (EditText) findViewById(R.id.etLName);
        button_Camera= (Button) findViewById(R.id.button_Camera);
        url_link =  (EditText) findViewById(R.id.edit_text);
        imageView = findViewById(R.id.img);
        button_Time.setOnClickListener(this);
        button_Date.setOnClickListener(this);
        button_Image.setOnClickListener(this);
        button_OpenCite.setOnClickListener(this);
        button_Camera.setOnClickListener(this);
        button_SetName.setOnClickListener(this);
        findViewById(R.id.color).setOnClickListener(this);
        findViewById(R.id.align).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.button_Time:
                intent = new Intent("android.example.intent.showtime");
                intent.putExtra("lname", editText_Name.getText().toString());
                startActivity(intent);
                break;
            case R.id.button_Date:
                intent = new Intent("android.example.intent.showdate");
                intent.putExtra("lname", editText_Name.getText().toString());
                startActivity(intent);
                break;
            case R.id.button_SetName:
                intent = new Intent(this, InputName.class);
                intent.putExtra("lname", editText_Name.getText().toString());
                GetResultFromIntent.launch(intent);
                break;
            case R.id.button_OpenCite:
                String url = url_link.getText().toString();
                if (!url.startsWith("https://") && !url.startsWith("http://")) {
                    url = "http://" + url;
                }
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                break;
            case R.id.button_Camera:
                startActivity(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                break;
            case R.id.color:
                Intent color_intent = new Intent(this, ColorText.class);
                GetResultFromIntent.launch(color_intent);
                break;
            case R.id.align:
                Intent align_intent = new Intent(this, AlignActivity.class);
                GetResultFromIntent.launch(align_intent);
                break;
            case R.id.button_Image:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ImageFromCamera.launch(intent);
                break;
        }

    }
    ActivityResultLauncher<Intent> ImageFromCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result ) {
                    Intent data;
                    switch (result.getResultCode()) {
                        case -1:
                            data = result.getData();
                            Bundle extra = data.getExtras();
                            Bitmap image = (Bitmap) extra.get("data");
                            imageView.setImageBitmap(image);
                    }
                }
            });


    ActivityResultLauncher<Intent> GetResultFromIntent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result ) {
                    Intent data;
                    switch (result.getResultCode()) {
                        case InputName.RESULT_OK:
                            data = result.getData();
                            textview_Name = (TextView) findViewById(R.id.tvName);
                            String name = data.getStringExtra("name");
                            textview_Name.setText("Your name is " + name);
                            break;
                        case 1:
                            data = result.getData();
                            String colorInt = data.getStringExtra("color");
                            text_example.setTextColor(Integer.parseInt(colorInt));
                            break;
                        case 2:
                            data = result.getData();
                            switch (data.getStringExtra("align")) {
                                case "left":
                                    linearLayout.setGravity(Gravity.START);
                                    break;
                                case "center":
                                    linearLayout.setGravity(Gravity.CENTER);
                                    break;
                                case "rigth":
                                    linearLayout.setGravity(Gravity.END);
                                    break;
                            }
                    }
                }
            });
}