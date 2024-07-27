package com.example.project1.presentation.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.project1.R;

public class SetReminderFragment extends Fragment {

    private TimePicker timePicker;
    private EditText editTextMedicationName;
    private EditText editTextDosage;
    private Button buttonSetReminder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_reminder, container, false);

        timePicker = view.findViewById(R.id.timePicker);
        editTextMedicationName = view.findViewById(R.id.editTextMedicationName);
        editTextDosage = view.findViewById(R.id.editTextDosage);
        buttonSetReminder = view.findViewById(R.id.buttonSetReminder);

        buttonSetReminder.setOnClickListener(v -> {
            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();
            ((MainActivity4) getActivity()).setReminder(hour, minute);
        });

        return view;
    }
}