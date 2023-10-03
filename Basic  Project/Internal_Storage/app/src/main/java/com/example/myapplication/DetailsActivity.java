package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileInputStream;
public class DetailsActivity extends AppCompatActivity {
    FileInputStream fstream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView textView = findViewById(R.id.resultView);
        Button btnBack = findViewById(R.id.btnBack);
        try{
            fstream = openFileInput("user_details");
            StringBuffer stringBuffer = new StringBuffer();
            int i;
            while((i = fstream.read()) != -1){
                stringBuffer.append((char)i);
            }
            fstream.close();
            String details[] = stringBuffer.toString().split("\n");
            textView.setText("Name: "+details[0] + "\npasword: "+details[1]);
        }catch (Exception e){
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
