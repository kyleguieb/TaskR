package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static java.lang.Math.round;

public class Profile extends AppCompatActivity {

    // Vars for recycler view
    private ArrayList<UUID> mIds = new ArrayList<>();
    private ArrayList<String> mTaskTitles = new ArrayList<>();
    private ArrayList<String> mDatesNTimes = new ArrayList<>();
    private ArrayList<String> mDifficulties = new ArrayList<>();
    private ArrayList<String> mDateCreated = new ArrayList<>();
    private ArrayList<Task> listOfTasks = MainActivity.getTasks();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(Profile.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(Profile.this, CalendarActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    //startActivity(new Intent(Profile.this, Profile.class));
                    return true;
                case R.id.navigation_rewards:
                    startActivity(new Intent(Profile.this, Rewards.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_profile);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_notifications);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initTasks();
        defineButtons();
    }

    public void defineButtons() {
        //findViewById(R.id.starButton).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
//                case R.id.starButton:
//                    startActivity(new Intent(Profile.this, Themes.class));
//                    break;
            }
        }
    };


    private void initTasks() {

        //TODO: Should check to make sure it is working 100%


        for(int i = 0; i < listOfTasks.size(); i++)
        {
            if (listOfTasks.get(i).isCompleted()) {
                mIds.add(listOfTasks.get(i).getId());
                mTaskTitles.add(listOfTasks.get(i).getmName());
                mDatesNTimes.add(listOfTasks.get(i).getmDateAndTimeDue());
                mDifficulties.add(listOfTasks.get(i).getDifficulty());
                mDateCreated.add(listOfTasks.get(i).getDateCreated());
            }
        }

        initRecyclerView();
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewProfile);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mIds, mTaskTitles, mDatesNTimes); //needs to query mDifficulties and mDateCreated TODO

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



}