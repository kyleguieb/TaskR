package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

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
            }
            return false;
        }
    };
    private static ProgressBar mProgressBar;
    private TextView mExperienceCounter;
    private static TextView mLevels;

    //private int mProgressStatus = 0;
    private static int currentExp = 0;
    private int i = 0;
    private static int currentLevel = 0;


    Button testButton; //test button
    //private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mProgressBar = findViewById(R.id.progressingBar);
        mExperienceCounter = findViewById(R.id.textViewExperience);
        mLevels = findViewById(R.id.textViewLevel);

        testButton = findViewById(R.id.buttonToTest);
        //TODO - move this into a separate method?
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(i < 9)
                {
                    isLevelUp();
                    mProgressBar.incrementProgressBy(1);
                    currentExp +=1;
                    mExperienceCounter.setText(currentExp + "/100");
                    i++;
                }
                i = 0;
            }
        });



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    /*
    TODO - done
    Note to self: Image view, in XML, just change to TextView
    take out other stuff
    make text view to just an incrementing number
     */

    //Note: Level can't go past 3 digits otherwise it starts looking ugly, but
    // I mean you can just make a check
    // however comma it's pointless
    public static void isLevelUp()
    {
        if (mProgressBar.getProgress() == mProgressBar.getMax())
        {
            mProgressBar.setProgress(0);
            currentExp = 0;
            currentLevel++;
            mLevels.setText(currentLevel + ""); //no idea why you need to add a blank thing but ok
        }
    }

//        defineButtons();
//    }

    public void defineButtons() {
        findViewById(R.id.starButton).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.starButton:
                    startActivity(new Intent(Profile.this, Rewards.class));
                    break;
            }
        }
    };

}
