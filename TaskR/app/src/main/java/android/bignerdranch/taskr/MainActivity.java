package android.bignerdranch.taskr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ImageButton button; // invisible_calendar_button, invisible_profile_button;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        button = (ImageButton) findViewById(R.id.Direct);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                directMessageScreen();
            }
        });
//        invisible_calendar_button = findViewById(R.id.invisibleCalBtn);
//        invisible_calendar_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                calendarScreen();
//            }
//        });
//        invisible_profile_button = findViewById(R.id.invisibleProfileBtn);
//        invisible_profile_button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                profileScreen();
//            }
//        });
    }

    public void directMessageScreen()
    {
        //startActivity(new Intent(MainActivity.this,Login.class));
        Intent intent = new Intent(this, DirectMessageChat.class);
        startActivity(intent);
    }

//    public void calendarScreen()
//    {
//        //startActivity(new Intent(MainActivity.this,Login.class));
//        Intent intent2 = new Intent(this, Calendar.class);
//        startActivity(intent2);
//    }
//
//    public void profileScreen()
//    {
//        //startActivity(new Intent(MainActivity.this,Login.class));
//        Intent intent3 = new Intent(this, Calendar.class);
//        startActivity(intent3);
//    }

}
