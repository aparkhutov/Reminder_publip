package ru.nsu.mukhortov.reminder.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

import ru.nsu.mukhortov.reminder.database.Task;

public class DatabaseHelper extends SQLiteOpenHelper{
/*database*/
    static final String dbName="rapidminerDB";

    static final String tasksTable="tasks";
    static final String colDescription="description";

    static final String recurrentTable="recurrent";
    static final String colFDay="f_day";
    static final String colFTime="f_time";

    static final String nonRecurrentTable="non_recurrent";
    static final String colSTime="start_time";
    static final String colPeriod="period";

    static final String perspectiveTable="perspective";

    static final String pNoticesTable="p_notices";

    static final String noticesTable="notices";

    static final String colID="id";
    static final String colTaskID="task_id";
    static final String colNoticeID="notice_id";
    static final String colName="name";
    static final String colStatus="status";
    static final String colTime="time";
    static final String colNoticeText="notice_text";

    private int taskID = 0;
    private int nonRecID = 0;
    private int noticeID = 0;

    private HashMap<Integer, Task> tasks = new HashMap<>();

    public DatabaseHelper(Context context){
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tasksTable + " (" +
                colID + " INTEGER PRIMARY KEY , " +
                colName + " TEXT, " +
                colDescription + " TEXT, " +
                colStatus + " TEXT)");

        db.execSQL("CREATE TABLE "+ nonRecurrentTable + " (" +
                colID +" INTEGER PRIMARY KEY , " +
                colTaskID + " Integer, " +
                colNoticeID + " Integer, " +
                colFDay + " Date, " +
                colFTime + " Time)");

        db.execSQL("CREATE TABLE "+ recurrentTable + " (" +
                colID + " INTEGER PRIMARY KEY , " +
                colTaskID + " Integer, " +
                colNoticeID + " Integer, " +
                colSTime + " Time, " +
                colPeriod + " Integer, " +
                colStatus + " TEXT)");

        db.execSQL("CREATE TABLE "+ perspectiveTable + " (" +
                colID + " INTEGER PRIMARY KEY , " +
                colTaskID + " Integer, " +
                colNoticeID + " Integer, " +
                colStatus + " TEXT)");

        db.execSQL("CREATE TABLE "+ pNoticesTable + " (" +
                colID + " INTEGER PRIMARY KEY , " +
                colTime + " Time, " +
                colNoticeText + " TEXT)");

        db.execSQL("CREATE TABLE "+ noticesTable + " (" +
                colID + " INTEGER PRIMARY KEY , " +
                colTime + " Time, " +
                colNoticeText + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tasksTable);
        db.execSQL("DROP TABLE IF EXISTS " + recurrentTable);
        db.execSQL("DROP TABLE IF EXISTS " + nonRecurrentTable);
        db.execSQL("DROP TABLE IF EXISTS " + perspectiveTable);
        db.execSQL("DROP TABLE IF EXISTS " + pNoticesTable);
        db.execSQL("DROP TABLE IF EXISTS " + noticesTable);

        onCreate(db);
    }

    public void addTask(String name, String description, String status,
                        String f_day, String f_time, String n_time){

        // ADDITION TO THE "TASKS" TABLE

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + colID + " FROM " + tasksTable ,new String[]{});

        int count = cursor.getCount();
        if (count == 0){
            taskID++;

        } else{
            cursor.moveToLast();
            String ID = cursor.getString(cursor.getColumnIndex(colID));
            taskID = Integer.parseInt(ID);
            taskID++;
        }

        db.execSQL("INSERT INTO " + tasksTable + " (" +
                colID + ", " +
                colName + ", " +
                colDescription + ", " +
                colStatus + ") VALUES (" +
                taskID + ", '" +
                name + "', '" +
                description + "', '" +
                status + "')");



        // ADDITION TO THE "NOTICES" TABLE

        cursor = db.rawQuery("SELECT " + colID + " FROM " + noticesTable ,new String[]{});

        count = cursor.getCount();
        if (count == 0){
            noticeID++;

        } else{
            cursor.moveToLast();
            String ID = cursor.getString(cursor.getColumnIndex(colID));
            noticeID = Integer.parseInt(ID);
            noticeID++;
        }

        db.execSQL("INSERT INTO " + noticesTable + " (" +
                colID + ", " +
                colTime + ", " +
                colNoticeText + ") VALUES (" +
                noticeID + ", '" +
                n_time + "', '" +
                name + "')");



        // ADDITION TO THE "NON_RECURRENT" TABLE

        cursor = db.rawQuery("SELECT " + colID + " FROM " + nonRecurrentTable ,new String[]{});

        count = cursor.getCount();

        if (count == 0){
            nonRecID++;

        } else{
            cursor.moveToLast();
            String ID = cursor.getString(cursor.getColumnIndex(colID));
            nonRecID = Integer.parseInt(ID);
            nonRecID++;
        }

        db.execSQL("INSERT INTO " + nonRecurrentTable + " (" +
                colID + ", " +
                colTaskID + ", " +
                colNoticeID + ", " +
                colFDay + ", " +
                colFTime + ") VALUES (" +
                nonRecID + ", " +
                taskID + ", " +
                noticeID + ", '" +
                f_day + "', '" +
                f_time + "')");

    }

    public HashMap<Integer, Task> getTasks(){
        SQLiteDatabase db = this.getReadableDatabase();
        String name = null;
        String description = null;
        String status = null;
        String finish_day = null;
        String finish_time = null;
        String notice_time = null;

        Cursor cursorT = db.rawQuery("SELECT * FROM " + tasksTable ,new String[]{});
        Cursor cursorNR;
        Cursor cursorN;

        cursorT.moveToFirst();

        int count = cursorT.getCount();
        int step = 0;
        String currentID;
        String noticeID;

        while (step < count){

            currentID = cursorT.getString(cursorT.getColumnIndex(colID));
            name = cursorT.getString(cursorT.getColumnIndex(colName));
            description = cursorT.getString(cursorT.getColumnIndex(colDescription));
            status = cursorT.getString(cursorT.getColumnIndex(colStatus));

            cursorNR = db.rawQuery("SELECT * FROM " + nonRecurrentTable + " WHERE task_id = " + currentID, new String[]{});

            cursorNR.moveToFirst();
            noticeID = cursorNR.getString(cursorNR.getColumnIndex(colNoticeID));
            finish_day = cursorNR.getString(cursorNR.getColumnIndex(colFDay));
            finish_time = cursorNR.getString(cursorNR.getColumnIndex(colFTime));

            cursorN = db.rawQuery("SELECT * FROM " + noticesTable + " WHERE " +
                    colID + " = " + noticeID, new String[]{});

            cursorN.moveToFirst();
            notice_time = cursorN.getString(cursorN.getColumnIndex(colTime));

            tasks.put(step, new Task(name, description, status, finish_day, finish_time, notice_time));

            step++;
            cursorT.moveToNext();
        }

        return tasks;
    }

    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tasksTable);
        db.execSQL("DROP TABLE IF EXISTS " + recurrentTable);
        db.execSQL("DROP TABLE IF EXISTS " + nonRecurrentTable);
        db.execSQL("DROP TABLE IF EXISTS " + perspectiveTable);
        db.execSQL("DROP TABLE IF EXISTS " + pNoticesTable);
        db.execSQL("DROP TABLE IF EXISTS " + noticesTable);

        onCreate(db);
    }
}
