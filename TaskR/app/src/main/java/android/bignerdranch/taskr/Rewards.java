package android.bignerdranch.taskr;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Rewards extends Activity implements OnClickListener {

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
                break;
//            case R.id.button3:
//                Utils.changeToTheme(this, Utils.THEME_BLUE);
//                break;
        }
    }

//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //setContentView(R.layout.activity_rewards);
////        final int theme = R.style.AppTheme;
////        getTheme().applyStyle(theme, true);
//        setContentView(R.layout.activity_rewards);
//
//        Button change = findViewById(R.id.setButton);
//        change.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                getApplication().setTheme(R.style.AppThemeMint);
////                recreate();
////                theme = R.style.AppThemeMint;
//                recreate();
//
//            }
//        });
//
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//    }


}