package com.example.todolistfinal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TodoTask implements Parcelable {

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

    protected TodoTask(Parcel in) {
        taskId = in.readInt();
        isChecked = in.readByte() != 0;
        taskName = in.readString();
        taskDesc = in.readString();
        dateTime = in.readString();
    }

    public static final Creator<TodoTask> CREATOR = new Creator<TodoTask>() {
        @Override
        public TodoTask createFromParcel(Parcel in) {
            return new TodoTask(in);
        }

        @Override
        public TodoTask[] newArray(int size) {
            return new TodoTask[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(taskId);
        parcel.writeByte((byte) (isChecked ? 1 : 0));
        parcel.writeString(taskName);
        parcel.writeString(taskDesc);
        parcel.writeString(dateTime);
    }
}
