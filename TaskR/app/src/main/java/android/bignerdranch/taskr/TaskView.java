package android.bignerdranch.taskr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class TaskView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        defineButtons();
    }

    private void defineButtons(){
        findViewById(R.id.editBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.deleteBtn).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.deleteBtn:

                case R.id.editBtn:

            }
        }
    };
    }

