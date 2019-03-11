package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CreatingTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_task);

        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.cancelBtn).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.cancelBtn:
                    startActivity(new Intent(CreatingTask.this, MainActivity.class));
                    break;
            }
        }
    };
}
