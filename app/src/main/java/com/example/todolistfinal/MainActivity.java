package com.example.todolistfinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;

import com.example.todolistfinal.Adapater.TaskAdapter;
import com.example.todolistfinal.Database.DBHelper;
import com.example.todolistfinal.model.TodoTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    
    //initialize
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<TodoTask> todoList = new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    DBHelper dbHelper;
    AlertDialog.Builder alertBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_to_refresh);

        recyclerView = findViewById(R.id.task_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        dbHelper = new DBHelper(getApplicationContext(), dbHelper.DATABASE_NAME, null, dbHelper.VERSION);
        alertBox = new AlertDialog.Builder(this);
        taskAdapter = new TaskAdapter(getApplicationContext(), dbHelper, alertBox);
        recyclerView.setAdapter(taskAdapter);

        if (savedInstanceState != null){
            todoList = savedInstanceState.getParcelableArrayList("todoList");
        }

        swipeRefreshLayout.setOnRefreshListener(() -> {
            taskAdapter.clear();
            todoList = dbHelper.getAllItems();
            taskAdapter.setTodoList(todoList);

            swipeRefreshLayout.setRefreshing(false);
        });





    }
    
    public void addTask(View view){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        todoList = dbHelper.getAllItems();
        taskAdapter.setTodoList(todoList);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList("todoList", todoList);
    }
}