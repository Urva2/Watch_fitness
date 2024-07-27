package com.example.project1.presentation.activities;

import static com.example.project1.R.id.ans;
import static com.example.project1.R.id.btn2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

public class activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Reference the EditText fields, Button, and TextView
        EditText editTextHeight = findViewById(R.id.editTextNumber);
        EditText editTextWeight = findViewById(R.id.editTextNumber2);
        Button buttonCalculate = findViewById(btn2);
        TextView textViewResult = findViewById(ans);

        // Set an OnClickListener to the button
        buttonCalculate.setOnClickListener(v -> {
            // Get the height and weight input from the user
            String heightStr = editTextHeight.getText().toString();
            String weightStr = editTextWeight.getText().toString();

            if (!heightStr.isEmpty() && !weightStr.isEmpty()) {
                // Convert the input to float
                float height = Float.parseFloat(heightStr);
                height=height/100;
                float weight = Float.parseFloat(weightStr);
                String structure = null;
                // Calculate the BMI
                float bmi = calculateBMI(height, weight);
                if(bmi<18.5) {
                    structure = "underweight";
                }
                else if (bmi>18&&bmi<24) {
                    structure ="healthy";
                }
                else if (bmi>25&&bmi<30.0) {
                    structure ="overweight";
                }
                else if (bmi>31) {
                    structure ="obese";
                }
                else{
                }

                String format = String.format("%s",structure);
                textViewResult.setText(format);
            } else {
                // Show a message if any of the input fields are empty
                Toast.makeText(activity2.this, "Please enter both height and weight", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to calculate BMI
    private float calculateBMI(float height, float weight) {
        return weight / (height * height);
    }
}


