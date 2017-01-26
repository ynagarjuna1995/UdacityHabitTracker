package io.github.ynagarjuna1995.udacityhabittracker.util;


public class HabitContract {

    public static final String DATABASE_NAME = "habits.db";
    public static final int DATABASE_VERSION = 1;

    /**
     * {@link DogWalkingTable#COLUMN_DURATION} - Tracks the Duration
     * {@link DogWalkingTable#COLUMN_DESC} - For writing the description for that particular walk
     * like ex: walked in park
     * {@link DogWalkingTable#DOG_TABLE_CREATE} - Create the DogWalkingTable
     * {@link DogWalkingTable#allColumns} -Can be Used by the read method of the Cursor nothing but a projection
     */

    static class DogWalkingTable {
        public static final String TABLE_DOGWALKING = "dogwalking";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_DURATION = "duration";
        public static final String COLUMN_DESC = "description";

        static final String DOG_TABLE_CREATE =
                "CREATE TABLE " + TABLE_DOGWALKING + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DESC + " TEXT, " +
                        COLUMN_DURATION + " NUMERIC " +
                        ")";
        public static final String[] allColumns = {
                DogWalkingTable.COLUMN_ID,
                DogWalkingTable.COLUMN_DESC,
                DogWalkingTable.COLUMN_DURATION
        };
    }

    /**
     * {@link WalkingHabitTable#COLUMN_NUM_STEPS} - Tracks the no of steps
     * {@link WalkingHabitTable#COLUMN_DESC} - For writing the description for that particular walk
     * like ex: walked in park
     * {@link WalkingHabitTable#STEP_TABLE_CREATE} - Create the Table
     */
    static class WalkingHabitTable {

        public static final String TABLE_STEPS = "walking";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NUM_STEPS = "numSteps";
        public static final String COLUMN_DESC = "description";
        public static final String STEP_TABLE_CREATE =
                "CREATE TABLE " + TABLE_STEPS + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_DESC + " TEXT, " +
                        COLUMN_NUM_STEPS + " NUMERIC " +
                        ")";
    }
}