package android.bignerdranch.taskr;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.bignerdranch.taskr.MainActivity;
import android.widget.EditText;
import android.widget.Toast;

public class CreatingTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_task);

        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.cancelBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.saveBtn).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.cancelBtn:
                    startActivity(new Intent(CreatingTask.this, MainActivity.class));
                    break;
                case R.id.saveBtn:
                    //get info from EditTexts and make a new task object
                    EditText inputName = findViewById(R.id.TitleCreate_editText);
                    EditText inputDescription = findViewById(R.id.DescriptionCreate_editText);
                    EditText inputTime = findViewById(R.id.TimeCreate_editText);
                    EditText inputDate = findViewById(R.id.DateCreate_editText);

                    //adding that new task object to the database itself
                    Task newTask = new Task(inputName.getText().toString(), inputDescription.getText().toString(),
                                            inputDate.getText().toString() + " at " +
                                            inputTime.getText().toString());
                    MainActivity.addTask(newTask);

                    startActivity(new Intent(CreatingTask.this, MainActivity.class));
                    break;
            }
        }
    };
}
