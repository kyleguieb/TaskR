package android.bignerdranch.taskr;

import android.bignerdranch.taskr.database.TaskBaseHelper;
import android.bignerdranch.taskr.database.TaskCursorWrapper;
import android.bignerdranch.taskr.database.TaskDbSchema;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;


import java.util.ArrayList;

public class Calendar extends AppCompatActivity  {
//    private ArrayList<String> mTaskTitles = new ArrayList<>();
//    private ArrayList<String> mDatesNTimes = new ArrayList<>();
//    private Context mContext;
//    private static SQLiteDatabase mDatabase;


    //Creates the navigation bar
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(Calendar.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    //startActivity(new Intent(Calendar.this, Calendar.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(Calendar.this, Profile.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
//        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
//        mContext = getApplicationContext();

        //mTextMessage = (TextView) findViewById(R.id.message);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }
//
//
//    private void initRecyclerView() {
//        RecyclerView recyclerView = findViewById(R.id.RecyclerViewCalendar);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTaskTitles, mDatesNTimes);
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//    }
//
//
//
//    private void initTasks() { //Gotta figure this out, should be where it pulls from Database?
//
//        ArrayList<Task> listOfTasks = getTasks();
//
//        for(int i = 0; i < listOfTasks.size(); i++)
//        {
//            mTaskTitles.add(listOfTasks.get(i).getmName());
//            mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());
//        }
//
//        initRecyclerView();
//    }
//
//
//    private static ContentValues getContentValues(Task task)    {   //adds new task (should be in CreatingTask.java)
//        ContentValues values = new ContentValues();
//        values.put(TaskDbSchema.TaskTable.Cols.NAME, task.getmName());
//        values.put(TaskDbSchema.TaskTable.Cols.DESCRIPTION, task.getmDescription());
//        values.put(TaskDbSchema.TaskTable.Cols.DATE_AND_TIME_DUE, task.getmDateAndTimeDue());
//        //again, can modify if LocalDateAndTime gives us trouble
//
//        return values;
//    }
//
//
//    public static Task getTask(String name)    {       //get specific task by name
//        TaskCursorWrapper cursor = queryTasks(TaskDbSchema.TaskTable.Cols.NAME +
//                " = ?", new String[] {name});
//
//        try {
//            if (cursor.getCount() == 0) {
//                return null;
//            }
//
//            cursor.moveToFirst();
//            return cursor.getTask();
//        } finally {
//            cursor.close();
//        }
//    }
//
//
//    public static void updateTask(String originalTaskName, Task task)   {   //edits task accordingly, should be in TaskView.java
//
//        //new task that will replace old task
//        ContentValues values = getContentValues(task);
//
//        mDatabase.update(TaskDbSchema.TaskTable.NAME, values, TaskDbSchema.TaskTable.Cols.NAME
//                + " = ?", new String[] { originalTaskName });
//    }
//
//    public static void deleteTask(String taskName)    {
//        mDatabase.delete(TaskDbSchema.TaskTable.NAME, TaskDbSchema.TaskTable.Cols.NAME
//                + " = ?", new String[] {taskName});
//        //deletes tasks by searching by name (should change in the future, could accidentally delete a different task)
//    }
//
//    private static TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs)   {   //reading from database using query
//        Cursor cursor = mDatabase.query(
//                TaskDbSchema.TaskTable.NAME,
//                null,    //columns - null selects all columns
//                whereClause,
//                whereArgs,
//                null,
//                null,
//                null
//        );
//
//        return new TaskCursorWrapper(cursor);
//    }
//
//    public ArrayList<Task> getTasks()    {       //get all tasks in the database and put them in an ArrayList
//        ArrayList<Task> tasks = new ArrayList<>();
//
//        TaskCursorWrapper cursor = queryTasks(null,null);
//
//        try {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast())   {
//                tasks.add(cursor.getTask());
//                cursor.moveToNext();
//            }
//        }   finally {
//            cursor.close();
//        }
//        return tasks;
//    }
}
