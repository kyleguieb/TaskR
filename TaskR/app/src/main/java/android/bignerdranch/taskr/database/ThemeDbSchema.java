package android.bignerdranch.taskr.database;

public class ThemeDbSchema {
    public static final class ThemeTable {
        public static final String NAME = "themes";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String LEVEL_REQUIREMENT = "levelRequirement";
            public static final String UNLOCKED = "unlocked";
            public static final String THEME = "theme";
            public static final String DESCRIPTION = "description";
        }
    }
}
