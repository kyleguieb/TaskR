package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.Task;
import android.database.Cursor;
import android.database.CursorWrapper;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask()   {   //pull out relevant column data
        String name = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.NAME));
        String description = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.NAME));
        String dateAndTimeDue = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.DATE_AND_TIME_DUE));

        return null;
    }
}
