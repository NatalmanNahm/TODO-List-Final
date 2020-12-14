package com.example.todolistfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todolistfinal.Adapater.TaskAdapter;
import com.example.todolistfinal.Database.DBHelper;
import com.example.todolistfinal.model.TodoTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    //initialize
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<TodoTask> todoList = new ArrayList<>();
    DBHelper dbHelper;
    AlertDialog.Builder alertBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        dbHelper = new DBHelper(getApplicationContext(), dbHelper.DATABASE_NAME, null, dbHelper.VERSION);
        alertBox = new AlertDialog.Builder(this);
        taskAdapter = new TaskAdapter(getApplicationContext(), dbHelper, alertBox);
        recyclerView.setAdapter(taskAdapter);

    }
    
    public void addTask(View view){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoList = dbHelper.getAllItems();
        taskAdapter.setTodoList(todoList);
    }
}