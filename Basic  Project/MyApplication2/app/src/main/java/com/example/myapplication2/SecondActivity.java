package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    Bundle bundle=getIntent().getExtras();
    if(bundle!=null){
        String imgIndex=bundle.getString("ImageIndex");
        setImages(imgIndex);
    }
    private void setImages(String index){
        ImageView imageView=(ImageView) findViewById(R.id.imageView1);

        switch (index){
            case "1":
                imageView.setImageResource(R.drawable.logo1);
                 break;
            case "2":
                imageView.setImageResource(R.drawable.logo2);
                break;
            case "3":
                imageView.setImageResource(R.drawable.logo3);
                break;

            default:
                imageView.setImageResource(R.drawable.logo1);

        }

    }
}