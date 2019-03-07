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
    //private ImageButton direct_messages; // invisible_calendar_button, invisible_profile_button;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    startActivity(new Intent(MainActivity.this, Calendar.class));
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
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

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        defineButtons();

//        direct_messages = findViewById(R.id.DirectBtn);
//        direct_messages.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v)
//            {
//                directMessageScreen();
//            }
//        });
    }

    public void defineButtons() {
        findViewById(R.id.DirectBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.New_Task_floatingActionButton).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.DirectBtn:
                    startActivity(new Intent(MainActivity.this, DirectMessagesList.class));
                    break;
                case R.id.New_Task_floatingActionButton:
                    startActivity(new Intent(MainActivity.this, CreatingTask.class));
            }
        }
    };

//    public void directMessageScreen()
//    {
//        //startActivity(new Intent(MainActivity.this,DirectMessagesList.class));
//        Intent intent = new Intent(this, DirectMessagesList.class);
//        startActivity(intent);
//    }

}
