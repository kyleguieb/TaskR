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
    private ArrayList<Integer> mPrimaryColors = new ArrayList<>();
    private ArrayList<Integer> mPrimaryDarkColors = new ArrayList<>();
    private ArrayList<Integer> mAccentColors = new ArrayList<>();

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
                case R.id.navigation_rewards:
                    //startActivity(new Intent(Rewards.this, Rewards.class));
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
        findViewById(R.id.defaultTheme).setOnClickListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_rewards);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initTasks();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.defaultTheme:
                Utils.changeToTheme(this, Utils.THEME_DEFAULT);
                break;
            case R.id.setButton:
                Utils.changeToTheme(this, Utils.THEME_LILAC);
                //Utils.changeToTheme(MainActivity.class, Utils.THEME_MINT);

                //Utils.changeToTheme(this, Utils.THEME_ECHO);
                //Utils.changeToTheme(this, Utils.THEME_SNS);
                //Utils.changeToTheme(this, Utils.THEME_QP);
                //Utils.changeToTheme(this, Utils.THEME_WISHCRAFT);
                break;
        }
    }

    private void initTasks() {

        mThemes.add("Default");
        mLevels.add("1");
        mPrimaryColors.add(R.color.colorPrimary);
        mPrimaryDarkColors.add(R.color.colorPrimaryDark);
        mAccentColors.add(R.color.colorAccent);

        mThemes.add("Lilac Theme");
        mLevels.add("5");
        mPrimaryColors.add(R.color.lilacColorPrimary);
        mPrimaryDarkColors.add(R.color.lilacColorPrimaryDark);
        mAccentColors.add(R.color.lilacColorAccent);

        mThemes.add("Mint Theme");
        mLevels.add("10");
        mPrimaryColors.add(R.color.mintColorPrimary);
        mPrimaryDarkColors.add(R.color.mintColorPrimaryDark);
        mAccentColors.add(R.color.mintColorAccent);

        mThemes.add("Echo Theme");
        mLevels.add("350");
        mPrimaryColors.add(R.color.echoColorPrimary);
        mPrimaryDarkColors.add(R.color.echoColorPrimaryDark);
        mAccentColors.add(R.color.echoColorAccent);

        mThemes.add("Swords and Sorcery Theme");
        mLevels.add("350");
        mPrimaryColors.add(R.color.SnSColorPrimary);
        mPrimaryDarkColors.add(R.color.SnSColorPrimaryDark);
        mAccentColors.add(R.color.SnSColorAccent);

        mThemes.add("QP Theme");
        mLevels.add("350");
        mPrimaryColors.add(R.color.QPColorPrimary);
        mPrimaryDarkColors.add(R.color.QPColorPrimaryDark);
        mAccentColors.add(R.color.QPColorAccent);

        mThemes.add("WishCraft Theme");
        mLevels.add("350");
        mPrimaryColors.add(R.color.WCColorPrimary);
        mPrimaryDarkColors.add(R.color.WCColorPrimaryDark);
        mAccentColors.add(R.color.WCColorAccent);

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.RecyclerViewRewards);
        OtherAdapter adapter = new OtherAdapter(this, mThemes, mLevels, mPrimaryColors, mPrimaryDarkColors, mAccentColors);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}