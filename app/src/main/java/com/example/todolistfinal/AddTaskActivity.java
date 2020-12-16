package com.example.todolistfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolistfinal.Database.DBHelper;

import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    private Button mAddBtn;
    private EditText mTaskName;
    private EditText mDesc;
    private EditText mDateEditText;
    private DatePickerDialog mDatePickerDialog;
    private EditText mTimeEditText;
    private TimePickerDialog mTimePickerDialog;
    private String amPm;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        final Calendar calendar = Calendar.getInstance();
        mTaskName = findViewById(R.id.task_edit_text);
        mDesc = findViewById(R.id.note_edit_text);
        mDateEditText = findViewById(R.id.date_edit_text);
        mTimeEditText = findViewById(R.id.time_edit_text);

        if (savedInstanceState != null){
            mTaskName.setText(savedInstanceState.getString(DBHelper.COL_TASK_NAME));
            mDesc.setText(savedInstanceState.getString(DBHelper.COL_TASK_DESC));
            mDateEditText.setText(savedInstanceState.getString("date"));
            mTimeEditText.setText(savedInstanceState.getString("time"));
        }

        /**
         * Code to get date picker working on the editText
         */
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
        mTimeEditText.setInputType(InputType.TYPE_NULL);
        mTimeEditText.setOnClickListener(view -> {
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
            int currentMinute = calendar.get(Calendar.MINUTE);

            mTimePickerDialog = new TimePickerDialog(AddTaskActivity.this,
                    (timePicker, hourOfDay, min) -> {
                        int hour = hourOfDay % 12;
                        if (hour == 0) hour = 12;
                        String _AM_PM = (hourOfDay > 12) ? "PM" : "AM";
                        mTimeEditText.setText(String.format(Locale.getDefault(), "%02d:%02d %s", hour, min, _AM_PM));
                    }, currentHour, currentMinute, false);
            mTimePickerDialog.show();
        });

        dbHelper = new DBHelper(getApplicationContext(), dbHelper.DATABASE_NAME, null, dbHelper.VERSION);

        /**
         * what will happen when the button is pressed
         */
        mAddBtn = findViewById(R.id.add_btn);
        mAddBtn.setOnClickListener(view -> {
            String name = mTaskName.getText().toString();
            String desc = mDesc.getText().toString();
            String dateTime = mDateEditText.getText().toString() + ", " +
                    mTimeEditText.getText().toString();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(desc) ||
                    TextUtils.isEmpty(mDateEditText.getText().toString()) ||
                    TextUtils.isEmpty(mTimeEditText.getText().toString())){

                Toast toast = Toast.makeText(this, "Error! add Entries", Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toastView.setBackgroundResource(R.drawable.rounded_container);
                toast.show();
            } else {
                dbHelper.insertItem(name, desc, dateTime, false);
                finish();
            }



        });

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(DBHelper.COL_TASK_NAME, mTaskName.getText().toString());
        outState.putString(DBHelper.COL_TASK_DESC, mDesc.getText().toString());
        outState.putString("date", mDateEditText.getText().toString());
        outState.putString("time", mTimeEditText.getText().toString());

    }
}