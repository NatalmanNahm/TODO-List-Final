package com.example.todolistfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    private Button mAddBtn;
    private EditText mTaskName;
    private EditText mDesc;
    private EditText mDateEditText;
    private DatePickerDialog mDatePickerDialog;
    private EditText mTimeEditText;
    private TimePickerDialog mTimePickerDialog;
    private String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        final Calendar calendar = Calendar.getInstance();
        mTaskName = findViewById(R.id.task_edit_text);
        mDesc = findViewById(R.id.note_edit_text);

        /**
         * Code to get date picker working on the editText
         */
        mDateEditText = findViewById(R.id.date_edit_text);
        mDateEditText.setInputType(InputType.TYPE_NULL);
        mDateEditText.setOnClickListener(view -> {
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);

            mDatePickerDialog = new DatePickerDialog(AddTaskActivity.this,
                    (datePicker, year1, month1, day1) ->
                            mDateEditText.setText(day1 + "/" + (month1 + 1) + "/" + year1),
                    year, month, day);
            mDatePickerDialog.show();
        });

        /**
         * Code to get the timePicker working on the editText
         */
        mTimeEditText = findViewById(R.id.time_edit_text);
        mTimeEditText.setInputType(InputType.TYPE_NULL);
        mTimeEditText.setOnClickListener(view -> {
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            mTimePickerDialog = new TimePickerDialog(AddTaskActivity.this,
                    (timePicker, hour, min) -> {
                        if (hour >= 12) {
                            amPm = " PM";
                        } else {
                            amPm = " AM";
                        }
                        mTimeEditText.setText(String.format("%02d:%02d", hour, min) + amPm);
                    }, currentHour, currentMinute, false);
            mTimePickerDialog.show();
        });

        /**
         * what will happen when the button is pressed
         */
        mAddBtn = findViewById(R.id.add_btn);
        mAddBtn.setOnClickListener(view -> {
            String name = mTaskName.getText().toString();
            String desc = mDesc.getText().toString();
            String dateTime = mDateEditText.getText().toString() + ", " +
                    mTimeEditText.getText().toString();

            Intent intent = new Intent();
            intent.putExtra("name", name);
            intent.putExtra("desc", desc);
            intent.putExtra("dateTime", dateTime);
            Log.i("NAMETASK", dateTime);

            setResult(RESULT_OK, intent);
            finish();

        });

    }


}