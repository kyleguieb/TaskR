package android.bignerdranch.taskr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TaskView extends AppCompatActivity {

    private String mTaskName;
    private static final String EXTRA_TASK_NAME = "com.bignerdranch.android.taskr.extra_task_name";

    public static Intent newIntent(Context packageContext, String taskName) {
        Intent intent = new Intent(packageContext, TaskView.class); //for retrieving task name
        intent.putExtra(EXTRA_TASK_NAME, taskName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        mTaskName = getIntent().getStringExtra(EXTRA_TASK_NAME);

        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.editBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.deleteBtn).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())  {
                case R.id.editBtn:
                    EditText inputName = findViewById(R.id.TitleView_editText);
                    EditText inputDescription = findViewById(R.id.DescriptionView_editText);
                    EditText inputTime = findViewById(R.id.TimeView_editText);
                    EditText inputDate = findViewById(R.id.DateView_editText);

                    if (inputName.getText().toString().equals("") &&
                            inputDescription.getText().toString().equals("") &&
                            inputTime.getText().toString().equals("") &&
                            inputDate.getText().toString().equals("")) {
                                Toast.makeText(TaskView.this, "Please fill in at least one field!",
                                Toast.LENGTH_SHORT).show();
                                break;
                                //if not all fields are filled, restart TaskView activity
                    }

                    Task alteredTask = new Task(inputName.getText().toString(), inputDescription.getText().toString(),
                            inputDate.getText().toString() + " at " +
                                    inputTime.getText().toString());
                    MainActivity.updateTask(mTaskName, alteredTask);
                    startActivity(new Intent(TaskView.this, MainActivity.class));

                    break;
                case R.id.deleteBtn:
                    //delete from the database using method defined in Main Activity
                    MainActivity.deleteTask(mTaskName);
                    startActivity(new Intent(TaskView.this, MainActivity.class));
                    break;
            }

        }
    };
}
