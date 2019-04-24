package android.bignerdranch.taskr;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;

public class Rewards extends AppCompatActivity implements OnClickListener {

    private ArrayList<String> mThemes = new ArrayList<>();
    private ArrayList<String> mLevels = new ArrayList<>();
    private ArrayList<Integer> mColors = new ArrayList<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(Rewards.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(Rewards.this, CalendarActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(Rewards.this, Profile.class));
                    return true;
            }
            return false;
        }
    };


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_rewards);

        findViewById(R.id.setButton).setOnClickListener(this);
        //findViewById(R.id.button2).setOnClickListener(this);
        //findViewById(R.id.button3).setOnClickListener(this);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initTasks();
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        switch (v.getId())
        {
//            case R.id.button1:
//                Utils.changeToTheme(this, Utils.THEME_DEFAULT);
//                break;
            case R.id.setButton:
                Utils.changeToTheme(this, Utils.THEME_WHITE);
                //Utils.changeToTheme(MainActivity.class, Utils.THEME_WHITE);
                break;
//            case R.id.button3:
//                Utils.changeToTheme(this, Utils.THEME_BLUE);
//                break;
        }
    }

    private void initTasks() {

        mThemes.add("Default");
        mLevels.add("1");
        mColors.add(R.color.colorPrimary);

        mThemes.add("Lilac");
        mLevels.add("5");
        mColors.add(R.color.lilacColorPrimary);

        mThemes.add("Mint");
        mLevels.add("10");
        mColors.add(R.color.mintColorPrimary);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewRewards);
        OtherAdapter adapter = new OtherAdapter(this, mThemes, mLevels, mColors);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}