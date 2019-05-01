package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class Stickers extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(Stickers.this, MainActivity.class));
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(Stickers.this, CalendarActivity.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(Stickers.this, Profile.class));
                    return true;
                case R.id.navigation_rewards:
                    startActivity(new Intent(Stickers.this, Rewards.class));
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_stickers);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_rewards);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.previousPage).setOnClickListener(buttonClickListener);
        findViewById(R.id.nextPage).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.previousPage:

                    break;
                case R.id.nextPage:

                    break;
            }
        }
    };
}
