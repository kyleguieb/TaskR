package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.CalendarView;

import java.util.ArrayList;
import java.util.UUID;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private ArrayList<UUID> mIds = new ArrayList<>();
    private ArrayList<String> mTaskTitles = new ArrayList<>();
    private ArrayList<String> mDatesNTimes = new ArrayList<>();
    private CalendarView mCalendarView;
    private ArrayList<Task> listOfTasks = MainActivity.getTasks();
    private String currentMonth = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
    private String currentDay = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    private String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

    private String currentDate = currentMonth + "/" + currentDay + "/" + currentYear;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(CalendarActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    //startActivity(new Intent(Calendar.this, Calendar.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(CalendarActivity.this, Profile.class));
                    return true;
                case R.id.navigation_rewards:
                    startActivity(new Intent(CalendarActivity.this, Rewards.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_calendar);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mCalendarView = (CalendarView) findViewById(R.id.actualCalendar);

        for(int i = 0; i < listOfTasks.size(); i++)
        {
            if (!listOfTasks.get(i).isCompleted()) {
                String taskDateAndTimeTokens[] = listOfTasks.get(i).getmDateAndTimeDue().split(" at ");
                if (taskDateAndTimeTokens[0].compareTo(currentDate) == 0) {
                    mIds.add(listOfTasks.get(i).getId());
                    mTaskTitles.add(listOfTasks.get(i).getmName());
                    mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());

                }
            }
        }

        initRecyclerView();

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {

                String date = (month + 1) + "/" + dayOfMonth + "/" + year;

                mIds.clear();
                mTaskTitles.clear();
                mDatesNTimes.clear();

                for(int i = 0; i < listOfTasks.size(); i++)
                {
                    if (!listOfTasks.get(i).isCompleted()) {

                        String taskDateAndTimeTokens[] = listOfTasks.get(i).getmDateAndTimeDue().split(" at ");
                        if (taskDateAndTimeTokens[0].compareTo(date) == 0) {
                            mIds.add(listOfTasks.get(i).getId());
                            mTaskTitles.add(listOfTasks.get(i).getmName());
                            mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());

                        }
                    }
                }

                initRecyclerView();
            }
        });

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewCalendar);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mIds, mTaskTitles, mDatesNTimes);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
