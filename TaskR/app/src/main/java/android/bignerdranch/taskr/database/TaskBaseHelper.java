package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.database.TaskDbSchema.TaskTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "taskBase.db";

    public TaskBaseHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TaskTable.NAME + "("   +
                "  _id integer primary key autoincrement, " +       //a way of numbering and ordering database entries
                TaskTable.Cols.NAME + "," +
                TaskTable.Cols.DESCRIPTION+ "," +
                TaskTable.Cols.DATE_AND_TIME_DUE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)    {
        //dont worry about for now, do worry for future sprints
    }
}
