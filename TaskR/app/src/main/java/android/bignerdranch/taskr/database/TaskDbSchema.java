package android.bignerdranch.taskr.database;

public class TaskDbSchema {
    public static final class TaskTable {
        public static final String NAME = "tasks";

        public static final class Cols  {       //columns of the database
            public static final String NAME = "name";
            public static final String DESCRIPTION = "description";
            public static final String DATE_AND_TIME_DUE = "dateAndTimeDue";
        }
    }
}
