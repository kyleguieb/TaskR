package android.bignerdranch.taskr;

import java.time.LocalDateTime;

public class Task {


    private String mName;
    private String mDescription;
    private String mDateAndTimeDue;
    private boolean mCompleted;
    //private LocalDateTime dateAndTimeForNotification;   //this is the date and time before the task's due time that sends a popup notification to the user's phone
    //To be figured out later


    public Task(String taskName, String taskDescription, String taskDateAndTimeDue)
    {
        mName = taskName;
        mDescription = taskDescription;
        mDateAndTimeDue = taskDateAndTimeDue;
        mCompleted = false;     //starts off not completed
    }

    public Task(String taskName)
    {
        mName = taskName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmDateAndTimeDue() {
        return mDateAndTimeDue;
    }

    public void setmDateAndTimeDue(String mDateAndTimeDue) {
        this.mDateAndTimeDue = mDateAndTimeDue;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }
}
