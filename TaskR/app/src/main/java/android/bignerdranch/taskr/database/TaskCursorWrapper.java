package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.Task;
import android.database.Cursor;
import android.database.CursorWrapper;

import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.NAME;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask()   {   //pull out relevant column data
        String name = getString(getColumnIndex(NAME));
        String description = getString(getColumnIndex(NAME));
        String dateAndTimeDue = getString(getColumnIndex(TaskDbSchema.TaskTable.Cols.DATE_AND_TIME_DUE));

        Task task = new Task(name);
        task.setmDescription(description);
        task.setmDateAndTimeDue(dateAndTimeDue);
        return task;
    }
}
