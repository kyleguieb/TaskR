package android.bignerdranch.taskr.database;

import android.bignerdranch.taskr.Theme;
import android.database.Cursor;
import android.database.CursorWrapper;

public class ThemeCursorWrapper extends CursorWrapper {
    public ThemeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Theme getTheme() {
        String name = getString(getColumnIndex(ThemeDbSchema.ThemeTable.Cols.NAME));
        String levelRequirement = getString(getColumnIndex(ThemeDbSchema.ThemeTable.Cols.LEVEL_REQUIREMENT));
        int isUnlocked = getInt(getColumnIndex(ThemeDbSchema.ThemeTable.Cols.UNLOCKED));
        String theme = getString(getColumnIndex(ThemeDbSchema.ThemeTable.Cols.THEME));
        String description = getString(getColumnIndex(ThemeDbSchema.ThemeTable.Cols.DESCRIPTION));

        Theme themeObject = new Theme(name);
        themeObject.setLevelRequirement(levelRequirement);
        themeObject.setUnlocked(isUnlocked != 0);
        themeObject.setTheme(theme);
        themeObject.setDescription(description);

        return themeObject;
    }
}
