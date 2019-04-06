package android.bignerdranch.taskr;

import android.bignerdranch.taskr.database.TaskBaseHelper;
import android.bignerdranch.taskr.database.TaskCursorWrapper;
import android.bignerdranch.taskr.database.TaskDbSchema;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> Dev
    private Context mContext;
    private static SQLiteDatabase mDatabase;

    // Vars for RecyclerView
    private ArrayList<String> mTaskTitles = new ArrayList<>();
    private ArrayList<String> mDatesNTimes = new ArrayList<>();

    private TextView mTextMessage;
    //private ImageButton direct_messages; // invisible_calendar_button, invisible_profile_button;

<<<<<<< HEAD
>>>>>>> Tyler
=======
>>>>>>> Dev
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this, Calendar.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(MainActivity.this, Profile.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> Dev
        mContext = getApplicationContext();         //this line is super iffy, ask team members if problem persists
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();         //initialization of the database using SQLiteOpenHelper
        //see page 272 of big nerd ranch textbook for clarification

        mTextMessage = (TextView) findViewById(R.id.message);
<<<<<<< HEAD
>>>>>>> Tyler
=======

>>>>>>> Dev
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Spinner spinner = findViewById(R.id.filterHomeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this);

        defineButtons();

        initTasks();

    }

    private void initTasks() { //Gotta figure this out, should be where it pulls from Database?
        mTaskTitles.add("Figure out if recyclerView scroll on its on or actually " +
                "needs the scrollView, The app seems to be stuttering");
        mDatesNTimes.add("April 3, 2019 11:25 PM");

        mTaskTitles.add("Figure out how to connect this to database information");
        mDatesNTimes.add("April 3, 2019 10:58 AM");

        mTaskTitles.add("Test a really really really really really really really really really " +
                "really really really really really really long title to make sure the text wrapping works");
        mDatesNTimes.add("April 3, 2019 11:38 PM");

        mTaskTitles.add("Test more");
        mDatesNTimes.add("April 3, 2019 11:39 PM");

        mTaskTitles.add("Test even more");
        mDatesNTimes.add("April 3, 2019 11:39 PM");

        mTaskTitles.add("Test some more");
        mDatesNTimes.add("April 3, 2019 11:39 PM");

        mTaskTitles.add("Test even more more");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        mTaskTitles.add("Testing");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        mTaskTitles.add("If");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        mTaskTitles.add("The");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        mTaskTitles.add("Scrolling");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        mTaskTitles.add("Also");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        mTaskTitles.add("Works");
        mDatesNTimes.add("April 3, 2019 11:41 PM");

        ArrayList<Task> listOfTasks = getTasks();

        for(int i = 0; i < listOfTasks.size(); i++)
        {
            mTaskTitles.add(listOfTasks.get(i).getmName());
            mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewHome);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mTaskTitles, mDatesNTimes);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void defineButtons() {
        //findViewById(R.id.DirectBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.NewTask_floatingActionButton).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.NewTask_floatingActionButton:
                    startActivity(new Intent(MainActivity.this, CreatingTask.class));
                    break;
            }
        }
    };

    private static ContentValues getContentValues(Task task)    {   //adds new task (should be in CreatingTask.java)
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.TaskTable.Cols.NAME, task.getmName());
        values.put(TaskDbSchema.TaskTable.Cols.DESCRIPTION, task.getmDescription());
        values.put(TaskDbSchema.TaskTable.Cols.DATE_AND_TIME_DUE, task.getmDateAndTimeDue());
        //again, can modify if LocalDateAndTime gives us trouble

        return values;
    }

    public static void addTask(Task c) {       //adds new task (should be in CreatingTask.java)
        ContentValues values = getContentValues(c);

        mDatabase.insert(TaskDbSchema.TaskTable.NAME, null, values);
    }

    public Task getTask(String name)    {
        TaskCursorWrapper cursor = queryTasks(TaskDbSchema.TaskTable.Cols.NAME +
                " = ?", new String[] {name});

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getTask();
        } finally {
            cursor.close();
        }
    }

    public void updateTask(Task task)   {   //edits task accordingly, should be in TaskView.java
        String nameString = task.getmName();
        ContentValues values = getContentValues(task);

        mDatabase.update(TaskDbSchema.TaskTable.NAME, values, TaskDbSchema.TaskTable.Cols.NAME
                            + " = ?", new String[] { nameString });
    }

    private TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs)   {   //reading from database using query
        Cursor cursor = mDatabase.query(
                TaskDbSchema.TaskTable.NAME,
                null,    //columns - null selects all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TaskCursorWrapper(cursor);
    }

    public ArrayList<Task> getTasks()    {       //get all tasks in the database and put them in an ArrayList
        ArrayList<Task> tasks = new ArrayList<>();

        TaskCursorWrapper cursor = queryTasks(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast())   {
                tasks.add(cursor.getTask());
                cursor.moveToNext();
            }
        }   finally {
            cursor.close();
        }
        return tasks;
    }

}
