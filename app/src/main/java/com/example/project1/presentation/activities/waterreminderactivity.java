package com.example.project1.presentation.activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;
import com.example.project1.presentation.receiver.ReminderReceiver;

import java.util.Timer;
import java.util.TimerTask;

public class waterreminderactivity extends AppCompatActivity {

    private EditText editTextHour;
    private EditText editTextMinute;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static final String CHANNEL_ID = "WaterReminderChannel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTextHour = findViewById(R.id.editTextHour);
        editTextMinute = findViewById(R.id.editTextMinute);
        Button buttonStart = findViewById(R.id.buttonStart);
        Button buttonStop = findViewById(R.id.buttonStop);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        buttonStart.setOnClickListener(v -> startReminder());

        buttonStop.setOnClickListener(v -> stopReminder());

//        createNotificationChannel();
    }

    @SuppressLint({"ShortAlarm", "ScheduleExactAlarm"})
    private void startReminder() {
        Log.e("TAG", "startReminder: "+SystemClock.elapsedRealtime());
//        String hoursString = editTextHour.getText().toString();
//        String minutesString = editTextMinute.getText().toString();
//
//        if (hoursString.isEmpty() && minutesString.isEmpty()) {
//            Toast.makeText(this, "Please enter the number of hours and/or minutes", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        int hours = 0;
//        int minutes = 0;
//
//        if (!hoursString.isEmpty()) {
//            hours = Integer.parseInt(hoursString);
//        }
//
//        if (!minutesString.isEmpty()) {
//            minutes = Integer.parseInt(minutesString);
//        }

//        long intervalMillis = (hours * 3600000L) + (minutes * 60000L); // hours to milliseconds + minutes to milliseconds

//        long intervalMillis = 10000L; // 10 seconds
//        Intent intent = new Intent(this, ReminderReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
//                        10000,
//                        -20000,
//                        pendingIntent);
//                Log.d("Repeating","Every Time");
//            }
//
//        }, 0, 5000);

        Intent intent = new Intent(this, ReminderReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long interval = 10000; // 10 seconds
        alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis(),
                interval,
                pendingIntent
        );

        Toast.makeText(this, "Reminder started", Toast.LENGTH_SHORT).show();
    }

    private void stopReminder() {
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(this, "Reminder stopped", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel() {
        CharSequence name = "water_reminder";
        String description = "Channel for Water Reminder";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    public EditText getEditTextMinute() {
        return editTextMinute;
    }

    public void setEditTextMinute(EditText editTextMinute) {
        this.editTextMinute = editTextMinute;
    }

    public EditText getEditTextHour() {
        return editTextHour;
    }

    public void setEditTextHour(EditText editTextHour) {
        this.editTextHour = editTextHour;
    }
}
