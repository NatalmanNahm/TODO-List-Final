package com.example.todolistfinal.model;

public class TodoTask {

    //initialize
    private boolean isChecked;
    private String taskName;
    private String taskDesc;
    private String dateTime;

    public TodoTask(boolean isChecked, String taskName, String taskNote, String dateTime) {
        this.isChecked = isChecked;
        this.taskName = taskName;
        this.taskDesc = taskNote;
        this.dateTime = dateTime;
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
