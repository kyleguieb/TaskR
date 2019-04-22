package android.bignerdranch.taskr;

public class Theme {

    private String mName;
    private String mLevelRequirement;
    private boolean mUnlocked;
    private String mTheme;
    private String mDescription;

    public Theme(String themeName, String levelRequirement, String theme, String description)
    {
        mName = themeName;
        mLevelRequirement = levelRequirement;
        mTheme = theme;
        mDescription = description;
        mUnlocked = false;
    }

    public Theme(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getLevelRequirement() {
        return mLevelRequirement;
    }

    public boolean isUnlocked() {
        return mUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        mUnlocked = unlocked;
    }

    public String getTheme() {
        return mTheme;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setLevelRequirement(String levelRequirement) {
        mLevelRequirement = levelRequirement;
    }

    public void setTheme(String theme) {
        mTheme = theme;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
