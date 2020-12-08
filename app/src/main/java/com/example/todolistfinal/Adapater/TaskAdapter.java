package com.example.todolistfinal.Adapater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.model.TodoTask;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{

    private List<TodoTask> todoList;
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
