package com.example.todolistfinal.model;

public class TodoTask {

    //initialize
    private boolean isChecked;
    private String taskName;
    private String taskNote;
    private String dateTime;

    public TodoTask(boolean isChecked, String taskName, String taskNote, String dateTime) {
        this.isChecked = isChecked;
        this.taskName = taskName;
        this.taskNote = taskNote;
        this.dateTime = dateTime;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public String getDateTime() {
        return dateTime;
    }
}
