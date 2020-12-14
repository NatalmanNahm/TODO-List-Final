package com.example.todolistfinal.Adapater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolistfinal.Database.DBHelper;
import com.example.todolistfinal.R;
import com.example.todolistfinal.model.TodoTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<TodoTask> todoList = new ArrayList<>();
    DBHelper dbHelper;
    Context mContext;
    AlertDialog.Builder alertDialog;

    public TaskAdapter(Context context, DBHelper dbHelper, AlertDialog.Builder alertDialog) {
        mContext = context;
        this.todoList = dbHelper.getAllItems();
        this.dbHelper = dbHelper;
        this.alertDialog = alertDialog;
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
        TodoTask todoTask = todoList.get(position);

        holder.checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (holder.checkBox.isChecked()) {
                alertDialog.setMessage(R.string.box_message_true);
                alertDialog.setPositiveButton("Done", (dialogInterface, i) -> {
                    dbHelper.setDone(todoTask.getTaskId(), b);
                    todoList = dbHelper.getAllItems();
//                    notifyItemRangeChanged(todoList.indexOf(todoTask), todoList.size());
                    notifyDataSetChanged();
                });
                alertDialog.setNegativeButton("Delete", (dialogInterface, i) -> {
                    dbHelper.deleteItem(todoTask.getTaskId());
                    todoList = dbHelper.getAllItems();
                    notifyItemRemoved(holder.getAdapterPosition());
                });
                alertDialog.setNeutralButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
            } else {
                alertDialog.setMessage(R.string.box_message_false);
                alertDialog.setPositiveButton("add", (dialogInterface, i) -> {
                    dbHelper.setDone(todoTask.getTaskId(), b);
                    todoList = dbHelper.getAllItems();
//                    notifyItemRangeChanged(todoList.indexOf(todoTask), todoList.size());
                    notifyDataSetChanged();
                });
                alertDialog.setNegativeButton("Delete", (dialogInterface, i) -> {
                    dbHelper.deleteItem(todoTask.getTaskId());
                    todoList = dbHelper.getAllItems();
                    notifyItemRemoved(holder.getAdapterPosition());
                });
                alertDialog.setNeutralButton("Cancel", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
            }
            alertDialog.setTitle("Task Management");
            AlertDialog box = alertDialog.create();
            box.show();
        });
    }

    @Override
    public int getItemCount() {
        if (todoList == null) return 0;
        return todoList.size();
    }

    public void setTodoList(ArrayList<TodoTask> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.task)
        TextView taskName;
        @Bind(R.id.date_n_time)
        TextView dateTime;
        @Bind(R.id.desc)
        TextView desc;
        @Bind(R.id.checkBox)
        CheckBox checkBox;
        Context mContext;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bindTodo(TodoTask todoTask) {
            taskName.setText(todoTask.getTaskName());
            dateTime.setText(todoTask.getDateTime());
            checkBox.setChecked(todoTask.getIsChecked());
            desc.setText(todoTask.getTaskDesc());
        }
    }

}