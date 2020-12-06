package com.example.todolistfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class AddTaskActivity extends AppCompatActivity {

    private Button mAddBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        mAddBtn = findViewById(R.id.add_btn);
    }
}