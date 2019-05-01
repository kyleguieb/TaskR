package android.bignerdranch.taskr;

import java.util.UUID;
import java.util.Calendar;

public class Task{

    private UUID mId;
    private String mName;
    private String mDescription;
    private String mDateAndTimeDue;
    private boolean mCompleted;
    private String mDifficulty;
    private String mDateCreated;

    public Task(String taskName, String taskDescription, String taskDateAndTimeDue)
    {
        mId = UUID.randomUUID();
        mName = taskName;
        mDescription = taskDescription;
        mDateAndTimeDue = taskDateAndTimeDue;
        mDifficulty = "";
        mCompleted = false;     //starts off not completed

        String currentMonth = Integer.toString(Calendar.getInstance().get(Calendar.MONTH) + 1);
        String currentDay = Integer.toString(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        String currentYear = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));

        String currentDate = currentMonth + "/" + currentDay + "/" + currentYear;
        mDateCreated = currentDate;
    }

    public Task(UUID id)
    {
        mId = id;
    }

    public UUID getId() { return mId; }

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

    public String getDifficulty() {
        return mDifficulty;
    }

    public void setDifficulty(String difficulty) {
        mDifficulty = difficulty;
    }

    public String getDateCreated() {
        return mDateCreated;
    }

    public void setDateCreated(String dateCreated) {
        mDateCreated = dateCreated;
    }
}
