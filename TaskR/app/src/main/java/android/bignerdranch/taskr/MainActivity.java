package android.bignerdranch.taskr;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.bignerdranch.taskr.database.LevelAndExpBaseHelper;
import android.bignerdranch.taskr.database.LevelAndExpCursorWrapper;
import android.bignerdranch.taskr.database.LevelAndExpDbSchema;
import android.bignerdranch.taskr.database.TaskBaseHelper;
import android.bignerdranch.taskr.database.TaskCursorWrapper;
import android.bignerdranch.taskr.database.TaskDbSchema;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static Context mContext;
    private static SQLiteDatabase mDatabase;
    private static SQLiteDatabase mLevelAndExpDatabase;

    // Vars for RecyclerView
    private ArrayList<UUID> mIds = new ArrayList<>();
    private ArrayList<String> mTaskTitles = new ArrayList<>();
    private ArrayList<String> mDatesNTimes = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;

    // counts task finished for profile
    public static int globalTaskFinishedCounter = 0;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this, CalendarActivity.class));
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

        mContext = getApplicationContext();
        mDatabase = new TaskBaseHelper(mContext).getWritableDatabase();
        mLevelAndExpDatabase = new LevelAndExpBaseHelper(mContext).getWritableDatabase();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Spinner spinnerSort = findViewById(R.id.filterHomeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(adapter);
        //spinner.setOnItemSelectedListener(this); //TODO: Needs to be finished for sorting

        defineButtons();

        initTasks();

    }

//    protected View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
//    {
//        //View view = inflater.inflate(R.layout.)
//
//    }


    private void initTasks() {

        ArrayList<Task> listOfTasks = getTasks();

        for(int i = 0; i < listOfTasks.size(); i++)
        {
            if (!listOfTasks.get(i).isCompleted()) {
                mIds.add(listOfTasks.get(i).getId());
                mTaskTitles.add(listOfTasks.get(i).getmName());
                mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());
            }
        }

        initRecyclerView();
    }

    //initializes RecyclerView for the home screen
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewHome);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mIds, mTaskTitles, mDatesNTimes);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    //defines new task button
    public void defineButtons() {
        findViewById(R.id.NewTask_floatingActionButton).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.NewTask_floatingActionButton:

                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                    LayoutInflater inflater = LayoutInflater.from(mContext);
                    View mView = inflater.inflate(R.layout.dialog_create, null);

                    final EditText inputName = (EditText) mView.findViewById(R.id.newTitle);
                    final TextView inputDate = (TextView) mView.findViewById(R.id.newDate);
                    final TextView inputTime = (TextView) mView.findViewById(R.id.newTime);
                    final EditText inputDescription = (EditText) mView.findViewById(R.id.newDescription);

                    Button mCancel = (Button) mView.findViewById(R.id.cancelButton);
                    Button mSave = (Button) mView.findViewById(R.id.saveButton);

//                    Spinner spinnerDifficulty = findViewById(R.id.filterHomeSpinner);
//                    ArrayAdapter<CharSequence> adapterDifficulty = ArrayAdapter.createFromResource(mContext, R.array.difficulty, android.R.layout.simple_spinner_item);
//                    adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spinnerDifficulty.setAdapter(adapterDifficulty);
//                    //spinner.setOnItemSelectedListener(this); //TODO: Needs to be finished for difficulty

                    mBuilder.setView(mView);
                    final AlertDialog dialog = mBuilder.create();
                    dialog.show();

                    inputDate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar cal = Calendar.getInstance();
                            int year = cal.get(Calendar.YEAR);
                            int month = cal.get(Calendar.MONTH);
                            int day = cal.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog dateDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                            dateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                            dateDialog.show();

                        }
                    });

                    mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            month = month + 1;

                            String date = month + "/" + dayOfMonth + "/" + year;
                            inputDate.setText(date);

                        }
                    };

                    inputTime.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Calendar cal = Calendar.getInstance();
                            int hour = cal.get(Calendar.HOUR);
                            int minutes = cal.get(Calendar.MINUTE);
                            boolean isTwentyFour = false;

                            TimePickerDialog timeDialog = new TimePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mTimeSetListener, hour, minutes, isTwentyFour);
                            timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
                            timeDialog.show();

                        }
                    });

                    mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            String time = hourOfDay + ":" + minute;
                            inputTime.setText(time);
                        }
                    };

                    mSave.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View view) {
                            //adding that new task object to the database itself

                            if (inputName.getText().toString().equals("") ||
                                inputDate.getText().toString().equals("") ||
                                inputTime.getText().toString().equals("") ||
                                inputDescription.getText().toString().equals(""))
                            {
                                Toast.makeText(MainActivity.this, "Please fill in all fields!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Task newTask = new Task(inputName.getText().toString(), inputDescription.getText().toString(),
                                        inputDate.getText().toString() + " at " +
                                                inputTime.getText().toString());
                                MainActivity.addTask(newTask);

                                Toast.makeText(MainActivity.this, "New Task Successfully added!",
                                        Toast.LENGTH_SHORT).show();  //for debugging purposes

                                dialog.dismiss();
                                recreate();
                            }
                        }
                    });

                    mCancel.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });

                    break;
            }
        }
    };

    //practically makes a new task
    private static ContentValues getContentValues(Task task)    {   //adds new task (should be in CreatingTask.java)
        ContentValues values = new ContentValues();
        values.put(TaskDbSchema.TaskTable.Cols.UUID, task.getId().toString());
        values.put(TaskDbSchema.TaskTable.Cols.NAME, task.getmName());
        values.put(TaskDbSchema.TaskTable.Cols.DESCRIPTION, task.getmDescription());
        values.put(TaskDbSchema.TaskTable.Cols.DATE_AND_TIME_DUE, task.getmDateAndTimeDue());
        values.put(TaskDbSchema.TaskTable.Cols.COMPLETED, task.isCompleted() ? 1 : 0);

        return values;
    }


    public static void addTask(Task c) {       //adds new task (should be in CreatingTask.java)
        ContentValues values = getContentValues(c);

        mDatabase.insert(TaskDbSchema.TaskTable.NAME, null, values);
    }

    public static Task getTask(UUID id)    {       //get specific task by uuid
        TaskCursorWrapper cursor = queryTasks(TaskDbSchema.TaskTable.Cols.UUID +
                " = ?", new String[] {id.toString()});

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

    public static void updateTask(UUID id, Task task)   {   //edits task accordingly

        //new task that will replace old task
        ContentValues values = getContentValues(task);

        mDatabase.update(TaskDbSchema.TaskTable.NAME, values, TaskDbSchema.TaskTable.Cols.UUID
                            + " = ?", new String[] { id.toString() });
    }


    public static void deleteTask(UUID taskID)    {
        mDatabase.delete(TaskDbSchema.TaskTable.NAME, TaskDbSchema.TaskTable.Cols.UUID
                            + " = ?", new String[] {taskID.toString()});

        //deletes tasks by searching by name (should change in the future, could accidentally delete a different task)



        //for test lol

    }

    private static TaskCursorWrapper queryTasks(String whereClause, String[] whereArgs)   {   //reading from database using query
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

    public static ArrayList<Task> getTasks()    {       //get all tasks in the database and put them in an ArrayList
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

    private static ContentValues getLevelAndExpContentValues(User user) {
        ContentValues values = new ContentValues();
        values.put(LevelAndExpDbSchema.LevelAndExpTable.Cols.NAME, user.getName());
        values.put(LevelAndExpDbSchema.LevelAndExpTable.Cols.LEVEL, user.getLevel());
        values.put(LevelAndExpDbSchema.LevelAndExpTable.Cols.EXP, user.getExpToNextLevel());

        return values;
    }

    public void addUser(User user) {
        ContentValues values = getLevelAndExpContentValues(user);

        mLevelAndExpDatabase.insert(LevelAndExpDbSchema.LevelAndExpTable.NAME, null, values);
    }

    public void updateUser(User user) {
        String nameString = user.getName();
        ContentValues values = getLevelAndExpContentValues(user);

        mDatabase.update(LevelAndExpDbSchema.LevelAndExpTable.NAME, values,
                LevelAndExpDbSchema.LevelAndExpTable.Cols.NAME + " = ?",
                new String[] {nameString});
    }

    private LevelAndExpCursorWrapper queryLevelAndExp(String whereClause, String[] whereArgs) {
        Cursor cursor = mLevelAndExpDatabase.query(
                LevelAndExpDbSchema.LevelAndExpTable.NAME,
                null,   //columns - null selects all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new LevelAndExpCursorWrapper(cursor);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        LevelAndExpCursorWrapper cursor = queryLevelAndExp(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                users.add(cursor.getExpAndLevel());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return users;
    }

    public User getUser(String name) {
        LevelAndExpCursorWrapper cursor = queryLevelAndExp(
                LevelAndExpDbSchema.LevelAndExpTable.Cols.NAME + " = ?",
                new String[] { name });

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getExpAndLevel();
        } finally {
            cursor.close();
        }
    }
}
