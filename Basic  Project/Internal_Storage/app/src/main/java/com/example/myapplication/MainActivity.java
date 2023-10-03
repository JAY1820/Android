package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
public class MainActivity extends AppCompatActivity {
    EditText uname, city;
    Button saveBtn;
    FileOutputStream fstream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = findViewById(R.id.txtName);
        city = findViewById(R.id.txtCity);
        saveBtn = findViewById(R.id.btnSave);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uName = uname.getText().toString()+ "\n";
                String cityname = city.getText().toString();
                try{
                    fstream = openFileOutput("user_details", MODE_PRIVATE);
                    fstream.write(uName.getBytes());
                    fstream.write(cityname.getBytes());
                    fstream.close();
                    Toast.makeText(MainActivity.this, "User Details Saved Successfully!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, DetailsActivity.class));
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}