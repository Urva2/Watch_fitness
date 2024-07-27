package com.example.project1.presentation.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {

    private static final int REQUEST_CODE_SCHEDULE_EXACT_ALARM = 1001;

    private TimePicker timePicker;
    private MaterialButton btnSetSleepTime;
    private TextView tvCurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        timePicker = findViewById(R.id.timePicker);
        btnSetSleepTime = findViewById(R.id.btnSetSleepTime);
        tvCurrentTime = findViewById(R.id.tvCurrentTime);

        updateCurrentTime();
        btnSetSleepTime.setOnClickListener(v -> checkAndSetSleepReminder());
    }

    private void updateCurrentTime() {
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    runOnUiThread(() -> {
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                        String currentTime = sdf.format(calendar.getTime());
                        tvCurrentTime.setText("Current Time: " + currentTime);
                    });
                    Thread.sleep(1000);  // Update time every second
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Preserve interrupt status
                e.printStackTrace();
            }
        });
        thread.start();
    }

    private void checkAndSetSleepReminder() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (getSystemService(AlarmManager.class).canScheduleExactAlarms()) {
                setSleepReminder();
            } else {
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                startActivityForResult(intent, REQUEST_CODE_SCHEDULE_EXACT_ALARM);
            }
        } else {
            setSleepReminder();
        }
    }

    private void setSleepReminder() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1);
        }

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, "Sleep reminder set for " + hour + ":" + String.format("%02d", minute), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Alarm Manager not available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SCHEDULE_EXACT_ALARM) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                if (getSystemService(AlarmManager.class).canScheduleExactAlarms()) {
                    setSleepReminder();
                } else {
                    Toast.makeText(this, "Permission denied to set exact alarm", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}