package com.example.todolistfinal.model;

public class TodoTask {

    //initialize
    private int taskId;
    private boolean isChecked;
    private String taskName;
    private String taskDesc;
    private String dateTime;

    public TodoTask(int taskId, boolean isChecked, String taskName, String taskDesc, String dateTime) {
        this.taskId = taskId;
        this.isChecked = isChecked;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
        this.dateTime = dateTime;
    }

    public int getTaskId() {
        return taskId;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public String getDateTime() {
        return dateTime;
    }
}
