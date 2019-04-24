package android.bignerdranch.taskr.database;

public class LevelAndExpDbSchema {
    public static final class LevelAndExpTable {
        public static final String NAME = "levelAndExpToNextLevel";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String LEVEL = "level";
            public static final String EXP = "expToNextLevel";
        }
    }
}
