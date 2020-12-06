package com.example.todolistfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    
    //initialize
    private FloatingActionButton mAddTaskFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mAddTaskFAB = findViewById(R.id.add_task_FAB);
    }
    
    public void addTask(View view){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }
}