package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.database.LevelAndExpDbSchema.LevelAndExpTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LevelAndExpBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "levelAndExpBase.db";

    public LevelAndExpBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + LevelAndExpTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                LevelAndExpTable.Cols.NAME + ", " +
                LevelAndExpTable.Cols.LEVEL + ", " +
                LevelAndExpTable.Cols.EXP +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
