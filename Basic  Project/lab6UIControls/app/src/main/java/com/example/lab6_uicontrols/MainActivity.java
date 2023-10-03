package com.example.lab6_uicontrols;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    TimePicker picker;
    Button btnGet;
    TextView tvw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvw = findViewById(R.id.textView1);
        picker = findViewById(R.id.timePicker1);
        picker.setIs24HourView(false); // Set to 12-hour format
        btnGet = findViewById(R.id.button1);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour, minute;
                String am_pm;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = picker.getHour();
                    minute = picker.getMinute();
                } else {
                    hour = picker.getCurrentHour();
                    minute = picker.getCurrentMinute();
                }

                if (hour >= 12) {
                    am_pm = "PM";
                    if (hour > 12) {
                        hour = hour - 12;
                    }
                } else {
                    am_pm = "AM";
                    if (hour == 0) {
                        hour = 12;
                    }
                }

                tvw.setText("Selected Time: " + hour + ":" + String.format("%02d", minute) + " " + am_pm);
            }
        });
    }
}
