package com.example.project1.presentation.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

public class activity2 extends AppCompatActivity {

    private EditText editTextHeight;
    private EditText editTextWeight;
    private EditText editTextResult;
    private Button btnCalculate;
    private Button btnMale;
    private Button btnFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Initialize the views
        editTextHeight = findViewById(R.id.adjust_height);
        editTextWeight = findViewById(R.id.adjust_weight);
        editTextResult = findViewById(R.id.editTextResult);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnMale = findViewById(R.id.btnMale);
        btnFemale = findViewById(R.id.btnFemale);

        // Set onClickListener for Calculate Button with animation
        btnCalculate.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click_animation));
            calculateBMI();
        });

        // Set onClickListener for Male Button with animation
        btnMale.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click_animation));
            // Add any additional logic for btnMale if needed
        });

        // Set onClickListener for Female Button with animation
        btnFemale.setOnClickListener(v -> {
            v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_click_animation));
            // Add any additional logic for btnFemale if needed
        });
    }

    private void calculateBMI() {
        String heightStr = editTextHeight.getText().toString();
        String weightStr = editTextWeight.getText().toString();

        // Check if height and weight fields are not empty
        if (!TextUtils.isEmpty(heightStr) && !TextUtils.isEmpty(weightStr)) {
            double height = Double.parseDouble(heightStr) / 100; // Convert height to meters
            double weight = Double.parseDouble(weightStr);

            // Calculate BMI
            double bmi = weight / (height * height);

            // Determine BMI category
            String chara;
            if (bmi < 18.5) {
                chara = "Underweight";
            } else if (bmi >= 18.5 && bmi < 24) {
                chara = "Healthy";
            } else if (bmi >= 25 && bmi < 30) {
                chara = "Overweight";
            } else {
                chara = "Obese";
            }

            // Display result in the EditText next to Calculate BMI button
            editTextResult.setText(chara);
        } else {
            // Show a message if any of the input fields are empty
            Toast.makeText(activity2.this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
        }
    }
}