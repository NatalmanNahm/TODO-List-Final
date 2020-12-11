package com.example.todolistfinal.Adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.R;
import com.example.todolistfinal.model.TodoTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

    private ArrayList<TodoTask> todoList = new ArrayList<>();
    Context mContext;

    public TaskAdapter(Context context, ArrayList<TodoTask> todoList) {
        mContext = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutListItem = R.layout.task_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutListItem, parent, shouldAttachToParentImmediately);
        return new TaskViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.TaskViewHolder holder, int position) {
        holder.bindTodo(todoList.get(position));
    }

    @Override
    public int getItemCount() {
        if (todoList==null) return 0;
        return todoList.size();
    }

    public void setTodoList(ArrayList<TodoTask> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.task) TextView taskName;
        @Bind(R.id.date_n_time) TextView dateTime;
        @Bind(R.id.checkBox) CheckBox checkBox;
        Context mContext;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindTodo(TodoTask todoTask){
            taskName.setText(todoTask.getTaskName());
            dateTime.setText(todoTask.getDateTime());
            checkBox.setChecked(todoTask.getIsChecked());
        }
    }
}
