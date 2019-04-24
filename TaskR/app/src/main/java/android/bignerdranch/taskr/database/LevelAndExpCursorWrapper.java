package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.User;
import android.database.Cursor;
import android.database.CursorWrapper;

public class LevelAndExpCursorWrapper extends CursorWrapper {
    public LevelAndExpCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getExpAndLevel() {
        String nameString = getString(getColumnIndex(LevelAndExpDbSchema.LevelAndExpTable.Cols.NAME));
        int level = getInt(getColumnIndex(LevelAndExpDbSchema.LevelAndExpTable.Cols.LEVEL));
        int expToNextLevel = getInt(getColumnIndex(LevelAndExpDbSchema.LevelAndExpTable.Cols.EXP));

        User user = new User(nameString);
        user.setLevel(level);
        user.setExpToNextLevel(expToNextLevel);

        return user;
    }
}
