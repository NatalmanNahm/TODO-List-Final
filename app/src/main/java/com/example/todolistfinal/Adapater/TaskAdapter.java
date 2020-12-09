package com.example.todolistfinal.Adapater;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.R;
import com.example.todolistfinal.model.TodoTask;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder>{

    private List<TodoTask> todoList;

    public TaskAdapter(List<TodoTask> todoList) {
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TodoTask todoTask = this.todoList.get(position);
        holder.getTaskName().setText(todoTask.getTaskName());
        holder.getDateTime().setText(todoTask.getDateTime());
        holder.getCheckBox().setChecked(todoTask.getIsChecked());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
