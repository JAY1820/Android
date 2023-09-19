package com.example.apk1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText id, name, designation, location;
    Button saveBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.txtName1);
        name = findViewById(R.id.txtName);
        designation = findViewById(R.id.txtDesignation);
        location = findViewById(R.id.txtLocation);
        saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String designation1 = designation.getText().toString();
                String location1 = location.getText().toString();

                DbHandler dbHandler = new DbHandler(MainActivity.this);
                dbHandler.insertUserDetails(name1, designation1, location1);

                intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Details Inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}