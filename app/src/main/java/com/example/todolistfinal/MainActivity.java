package com.example.todolistfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.todolistfinal.Adapater.TaskAdapter;
import com.example.todolistfinal.model.TodoTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    
    //initialize
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private ArrayList<TodoTask> todoList = new ArrayList<>();
    String name;
    String desc;
    String dateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.task_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        taskAdapter = new TaskAdapter(getApplicationContext(), todoList);
        recyclerView.setAdapter(taskAdapter);

    }
    
    public void addTask(View view){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                name = data.getStringExtra("name");
                Log.i("NAMETASK", name);
                desc = data.getStringExtra("desc");
                Log.i("NAMETASK", desc);
                dateTime = data.getStringExtra("dateTime");
                Log.i("NAMETASK", dateTime);

                TodoTask todoTask = new TodoTask(true, name, desc, dateTime);
                todoList.add(todoTask);
                taskAdapter.setTodoList(todoList);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        taskAdapter.setTodoList(todoList);
    }
}