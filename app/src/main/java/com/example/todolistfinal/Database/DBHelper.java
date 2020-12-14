package com.example.todolistfinal.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todolistfinal.model.TodoTask;

import java.util.ArrayList;

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

    public void setDone (int id, boolean done){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DONE, done);
        getWritableDatabase().update(TABLE_NAME, contentValues, "_id=?",
                new String[]{Integer.toString(id)});
    }


    public int insertItem(String name, String desc, String dateTime, boolean checked){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TASK_NAME, name);
        contentValues.put(COL_TASK_DESC, desc);
        contentValues.put(COL_DATE_TIME, dateTime);
        contentValues.put(COL_DONE, checked);
        return (int) getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<TodoTask> getAllItems(){
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, new String[]{TASK_ID, COL_TASK_NAME,
        COL_TASK_DESC, COL_DATE_TIME, COL_DONE},
                null, null, null, null, COL_DONE);

        ArrayList<TodoTask> items = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                TodoTask item = new TodoTask(
                        cursor.getInt(cursor.getColumnIndex(TASK_ID)),
                        cursor.getInt(cursor.getColumnIndex(COL_DONE)) == 1,
                        cursor.getString(cursor.getColumnIndex(COL_TASK_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_TASK_DESC)),
                        cursor.getString(cursor.getColumnIndex(COL_DATE_TIME))

                );
                items.add(item);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    public boolean deleteItem(int id){
        return getWritableDatabase().delete(TABLE_NAME, "_id=?",
                new String[]{Integer.toString(id)}) > 0;
    }
}
