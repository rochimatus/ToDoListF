package com.example.todolistf.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.todolistf.data.model.Task;

import java.util.ArrayList;
import java.util.Date;

public class TaskTableHandler implements TableHandler<Task>{
    DatabaseHelper dbHelper;

    public TaskTableHandler(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void create(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedTask.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.FeedTask.COLUMN_DATE, String.valueOf(task.getDate()));
        values.put(DatabaseContract.FeedTask.COLUMN_DESCRIPTION, task.getDescription());
        values.put(DatabaseContract.FeedTask.COLUMN_FINISHED, task.isFinished());

        long newRowId = db.insert(DatabaseContract.FeedTask.TABLE_NAME, null, values);
    }

    @Override
    public Task readById(String id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                DatabaseContract.FeedTask._ID,
                DatabaseContract.FeedTask.COLUMN_TITLE,
                DatabaseContract.FeedTask.COLUMN_DATE,
                DatabaseContract.FeedTask.COLUMN_DESCRIPTION,
                DatabaseContract.FeedTask.COLUMN_FINISHED,
        };

        // Filter results WHERE "id" = id
        String selection = DatabaseContract.FeedTask._ID + " = ?";
        String[] selectionArgs = { id };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DatabaseContract.FeedTask.COLUMN_TITLE + " DESC";

        Cursor cursor = db.query(
                DatabaseContract.FeedTask.TABLE_NAME,   // The table to query
                projection,                             // The array of columns to return (pass null to get all)
                selection,                              // The columns for the WHERE clause
                selectionArgs,                          // The values for the WHERE clause
                null,                          // don't group the rows
                null,                           // don't filter by row groups
                sortOrder                               // The sort order
        );

        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(
                cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask._ID))+"",
                cursor.getString(1),    //title
                new Date(cursor.getString(3)),    //date
                cursor.getString(2),    //desc
                cursor.getInt(4) == 1? true:false    //is_finished
        );//description
        return null;
    }

    @Override
    public ArrayList<Task> readAll() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseContract.FeedTask.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Task task = new Task(
                        cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseContract.FeedTask._ID))+"",
                        cursor.getString(1),    //title
                        new Date(cursor.getString(3)),    //date
                        cursor.getString(2),    //desc
                        cursor.getInt(4) == 1? true:false    //is_finished
                );//description

                taskList.add(task);
            } while (cursor.moveToNext());
        }

        // return contact list
        return taskList;
    }

    @Override
    public void update(Task task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // set New value
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FeedTask.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.FeedTask.COLUMN_DATE, task.getDate().toString());
        values.put(DatabaseContract.FeedTask.COLUMN_DESCRIPTION, task.getDescription());
        values.put(DatabaseContract.FeedTask.COLUMN_FINISHED, task.isFinished());

        // Which row to update, based on the title
        String selection = DatabaseContract.FeedTask._ID + " LIKE ?";
        String[] selectionArgs = { task.getId() };

        int count = db.update(
                DatabaseContract.FeedTask.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
    }

    @Override
    public void delete(Task task) {

    }
}
