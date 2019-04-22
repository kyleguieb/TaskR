package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.Theme;
import android.bignerdranch.taskr.database.ThemeDbSchema.ThemeTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ThemeBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    private static final String DATABASE_NAME = "themeBase.db";

    public ThemeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ThemeTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ThemeTable.Cols.NAME + ", " +
                ThemeTable.Cols.LEVEL_REQUIREMENT + ", " +
                ThemeTable.Cols.UNLOCKED + ", " +
                ThemeTable.Cols.THEME + ", " +
                ThemeTable.Cols.DESCRIPTION +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
