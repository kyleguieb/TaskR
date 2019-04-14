package android.bignerdranch.taskr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class TaskView extends AppCompatActivity {

    private static UUID mId;
    private String mOriginalTaskName;
    private String mOriginalDescription;
    private String mOriginalDateAndTime;
    private static final String EXTRA_TASK_NAME = "com.bignerdranch.android.taskr.extra_task_name";
    private static final String EXTRA_TASK_DESC = "com.bignerdranch.android.taskr.extra_task_description";
    private static final String EXTRA_TASK_DATE_AND_TIME = "com.bignerdranch.android.taskr.extra_task_date_and_time";

    public static Intent newIntent(Context packageContext, UUID id) {
        mId = id;
        Intent intent = new Intent(packageContext, TaskView.class); //for retrieving task name
        Task originalTask = MainActivity.getTask(id);
        intent.putExtra(EXTRA_TASK_NAME, originalTask.getmName());
        intent.putExtra(EXTRA_TASK_DESC, originalTask.getmDescription());
        intent.putExtra(EXTRA_TASK_DATE_AND_TIME, originalTask.getmDateAndTimeDue());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);
        mOriginalTaskName = getIntent().getStringExtra(EXTRA_TASK_NAME);
        mOriginalDateAndTime = getIntent().getStringExtra(EXTRA_TASK_DATE_AND_TIME);
        mOriginalDescription = getIntent().getStringExtra(EXTRA_TASK_DESC);

        TextView displayTitle = (TextView) findViewById(R.id.taskViewTitle);
        displayTitle.setText(mOriginalTaskName);
        TextView displayDateNTime = (TextView) findViewById(R.id.taskViewDateNTime);
        displayDateNTime.setText(mOriginalDateAndTime);
        //String hardCodedDescription = "THIS IS THE DESCRIPTION OKAY";
        TextView displayDesc = (TextView) findViewById(R.id.taskViewDescription);
        displayDesc.setText(mOriginalDescription);

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
                            inputDate.getText().toString() + " at " + inputTime.getText().toString());
                    MainActivity.updateTask(mId, alteredTask);
                    startActivity(new Intent(TaskView.this, MainActivity.class));

                    break;
                case R.id.deleteBtn:
                    //delete from the database using method defined in Main Activity
                    MainActivity.deleteTask(mId);
                    startActivity(new Intent(TaskView.this, MainActivity.class));
                    break;
            }

        }
    };
}
