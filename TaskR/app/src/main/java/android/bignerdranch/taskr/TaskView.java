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
                    //delete from the database using method defined in Main Activity
                    startActivity(new Intent(TaskView.this, MainActivity.class));
                    break;

                case R.id.editBtn:
                    EditText inputName = findViewById(R.id.TitleView_editText);
                    EditText inputDescription = findViewById(R.id.DescriptionView_editText);
                    EditText inputTime = findViewById(R.id.TimeView_editText);
                    EditText inputDate = findViewById(R.id.DateView_editText);

                    if (inputName.getText().toString().equals("") ||
                            inputDescription.getText().toString().equals("") ||
                            inputTime.getText().toString().equals("") ||
                            inputDate.getText().toString().equals("")) {
                        Toast.makeText(TaskView.this, "Please fill in all fields!",
                                Toast.LENGTH_SHORT).show();
                        break;
                        //shouldn't go back to main activity if all fields aren't completed
                        //startActivity(new Intent(TaskView.this, MainActivity.class));
                        //if not all fields are filled, restart TaskView activity
                    }

                    Task alteredTask = new Task(inputName.getText().toString(), inputDescription.getText().toString(),
                            inputDate.getText().toString() + " at " +
                                    inputTime.getText().toString());
                    MainActivity.updateTask(alteredTask);

                    startActivity(new Intent(TaskView.this, MainActivity.class));
                    break;
            }
        }
    };
}

