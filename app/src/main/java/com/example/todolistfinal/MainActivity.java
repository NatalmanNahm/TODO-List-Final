package com.example.todolistfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todolistfinal.Adapater.TaskAdapter;
import com.example.todolistfinal.model.TodoTask;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    //initialize
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<TodoTask> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(todoList);
        recyclerView.setAdapter(taskAdapter);

    }
    
    public void addTask(View view){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }
}