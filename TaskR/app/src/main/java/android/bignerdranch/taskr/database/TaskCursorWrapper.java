package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.Task;
import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.COMPLETED;
import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.DATE_AND_TIME_DUE;
import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.DATE_CREATED;
import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.DESCRIPTION;
import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.DIFFICULTY;
import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.NAME;
import static android.bignerdranch.taskr.database.TaskDbSchema.TaskTable.Cols.UUID;

public class TaskCursorWrapper extends CursorWrapper {
    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Task getTask()   {   //pull out relevant column data
        String idString = getString(getColumnIndex(UUID));
        String name = getString(getColumnIndex(NAME));
        String description = getString(getColumnIndex(DESCRIPTION));
        String dateAndTimeDue = getString(getColumnIndex(DATE_AND_TIME_DUE));
        int isCompleted = getInt(getColumnIndex(COMPLETED));
        String dateCreated = getString(getColumnIndex(DATE_CREATED));
        String difficulty = getString(getColumnIndex(DIFFICULTY));

        Task task = new Task(java.util.UUID.fromString(idString));
        task.setmName(name);
        task.setmDescription(description);
        task.setmDateAndTimeDue(dateAndTimeDue);
        task.setCompleted(isCompleted != 0);
        task.setDateCreated(dateCreated);
        task.setDifficulty(difficulty);
        return task;
    }
}
