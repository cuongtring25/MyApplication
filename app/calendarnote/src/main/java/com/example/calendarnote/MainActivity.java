package com.example.calendarnote;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    EditText noteEditText;
    Button saveButton;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        calendarView = findViewById(R.id.calendarView);
        noteEditText = findViewById(R.id.noteEditText);
        saveButton = findViewById(R.id.saveButton);
        calendarView.setOnDateChangeListener((view, y, m, d) -> {
            fileName = String.format("%02d_%02d_%04d", d, m + 1, y);
            noteEditText.setText("");
            try {
                FileInputStream fis = openFileInput(fileName);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader bufferedReader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                fis.close();
                noteEditText.setText(sb);
            } catch (IOException e) {
                e.printStackTrace();
            }

                });
        saveButton.setOnClickListener(view -> {
            String noteContent = noteEditText.getText().toString();
            try {
                FileOutputStream fos;
                // Ghi nội dung vào file
                if (fileName.length()==0){
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date(calendarView.getDate()));
                    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    String temp = String.format("%02d_%02d_%04d", dayOfMonth, month + 1, year);
                    fos = openFileOutput(temp,MODE_PRIVATE);
                }
                else {
                    fos = openFileOutput(fileName, MODE_PRIVATE);
                }
                fos.write(noteContent.getBytes());
                fos.close();
                Toast.makeText(this, "Đã lưu ghi chú", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Lỗi khi lưu ghi chú", Toast.LENGTH_LONG).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}