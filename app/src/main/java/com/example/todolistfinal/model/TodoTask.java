package com.example.todolistfinal.model;

public class TodoTask {

    //initialize
    private int isChecked;
    private String taskName;
    private String taskNote;
    private String dateTime;

    public TodoTask(int isChecked, String taskName, String taskNote, String dateTime) {
        this.isChecked = isChecked;
        this.taskName = taskName;
        this.taskNote = taskNote;
        this.dateTime = dateTime;
    }

    public int getIsChecked() {
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
