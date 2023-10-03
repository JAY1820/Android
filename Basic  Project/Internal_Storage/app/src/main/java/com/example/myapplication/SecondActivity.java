package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText editText = findViewById(R.id.edit_text);
        Button btnWrite = findViewById(R.id.btnWrite);
        Button btnRead = findViewById(R.id.btnRead);
        Button btnClear = findViewById(R.id.btnClear);

        if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        // writeData();
        } else {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
        }
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalStorageAvailable() && isExternalStorageReadable()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(getStorageDir("demo.txt"), true);
                        fileOutputStream.write(editText.getText().toString().getBytes());
                        fileOutputStream.write("\n".toString().getBytes());
                        fileOutputStream.close();
                        Toast.makeText(SecondActivity.this, "File Content is Written...!!", Toast.LENGTH_SHORT).show();
                        editText.setText("");
                    } catch (Exception e) {
                        Toast.makeText(SecondActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SecondActivity.this, "External Storage is Not Available..!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalStorageAvailable() && isExternalStorageReadable()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(getStorageDir("demo.txt"));
                        StringBuffer stringBuffer = new StringBuffer();
                        int c;
                        while ((c = fileInputStream.read()) != -1) {
                            stringBuffer.append((char) c);
                        }
                        editText.setText(stringBuffer);
                    } catch (Exception e) {
                    }
                } else {
                    Toast.makeText(SecondActivity.this, "External Storage is Not Available..!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalStorageAvailable() && isExternalStorageReadable()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(getStorageDir("demo.txt"));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Toast.makeText(SecondActivity.this, "File Content is Flush... ", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(SecondActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SecondActivity.this, "External Storage is Not Available..!!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            Toast.makeText(this, "Permission is Granted..!!", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public String getStorageDir(String fileName) {
        File file = new File(Environment.getExternalStorageDirectory(), "Demo");
        if (!file.mkdirs()) {
            file.mkdirs();
        }
        String filePath = file.getAbsolutePath() + File.separator + fileName;
        return filePath;
    }
}