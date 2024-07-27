package com.example.project1.presentation.activities;

import static com.example.project1.R.id.mdcnbtn;
import static com.example.project1.R.id.sleepreminderbtn;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "water_reminder";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        Button buttonCalculateDGI = findViewById(R.id.buttonCalculateDGI);
        buttonCalculateDGI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity2.class);
            startActivity(intent);
        });
Button Sleepbutton= findViewById(sleepreminderbtn);
Sleepbutton.setOnClickListener(v -> {
    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
    startActivity(intent);
});
Button mbtn= findViewById(mdcnbtn);
mbtn.setOnClickListener(v -> {
    Intent intent = new Intent(MainActivity.this,MainActivity4.class);
    startActivity(intent);
});
        Button waterReminderButton = findViewById(R.id.hydration);
        waterReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, waterreminderactivity.class);
                startActivity(intent);
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.water_reminder);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
