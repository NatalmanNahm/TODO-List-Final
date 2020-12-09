package com.example.todolistfinal.Adapater;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.R;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    private TextView taskName;
    private TextView DateTime;
    private CheckBox checkBox;

    public TaskViewHolder(@NonNull View itemView) {
        super(itemView);
        taskName = itemView.findViewById(R.id.task);
        DateTime = itemView.findViewById(R.id.date_n_time);
        checkBox = itemView.findViewById(R.id.checkBox);
    }

    public TextView getTaskName() {
        return taskName;
    }

    public TextView getDateTime() {
        return DateTime;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
