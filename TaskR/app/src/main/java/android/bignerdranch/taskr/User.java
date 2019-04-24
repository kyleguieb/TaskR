package android.bignerdranch.taskr;

public class User {

    private int mLevel;
    private int mExpToNextLevel;
    private String mName;

    public User(int level, int expToNextLevel)
    {
        mName = "user";
        mLevel = level;
        mExpToNextLevel = expToNextLevel;
    }

    public User(String name)
    {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getExpToNextLevel() {
        return mExpToNextLevel;
    }

    public void setExpToNextLevel(int expToNextLevel) {
        mExpToNextLevel = expToNextLevel;
    }
}
