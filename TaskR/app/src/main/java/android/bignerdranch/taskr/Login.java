package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        button = findViewById(R.id.login_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                homeScreen();
            }
        });
    }

    public void homeScreen()
    {
        //startActivity(new Intent(MainActivity.this,Login.class));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
