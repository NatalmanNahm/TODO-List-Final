package com.example.todolistfinal.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "ToDo Database";
    public static final String TABLE_NAME = "todo";
    public static final String COL_TASK_NAME = "name";
    public static final String COL_TASK_DESC = "description";
    public static final String COL_DATE_TIME = "dateTime";
    public static final String COL_DONE = "Done";
    public static final String TASK_ID = "_id";
    public static final int VERSION = 1;


    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TASK_NAME + " TEXT," +
                COL_TASK_DESC + " TEXT," +
                COL_DATE_TIME + " TEXT," +
                COL_DONE + " INTERGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
