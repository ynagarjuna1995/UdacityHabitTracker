package io.github.ynagarjuna1995.udacityhabittracker.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class HabitDBOpenHelper extends SQLiteOpenHelper {


    private static final String LOGTAG = "HabitTracker OpenHelper";
    private SQLiteDatabase mSQLiteDatabase;

    public HabitDBOpenHelper(Context context) {
        super(context, HabitContract.DATABASE_NAME, null, HabitContract.DATABASE_VERSION);
        mSQLiteDatabase = getWritableDatabase();
        Log.i(LOGTAG, "DB Helper Created ");
    }

    /**
     * Similar to activity this method triggers when we first install the app for installing the
     * database, creation of tables and the initial population of the tables should happen.
     *
     * @param database The database.
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        mSQLiteDatabase = database;
        database.execSQL(HabitContract.DogWalkingTable.DOG_TABLE_CREATE);
        database.execSQL(HabitContract.WalkingHabitTable.STEP_TABLE_CREATE);
        Log.i(LOGTAG, "Tables have been created");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * @link onUpgrade() is used for
     * 1. Adding the tables
     * 2. Any necessary changes like upgrading the databse,altering.
     *
     * Similar to another database SQlite do maintain its atomocity by performing the
     * below method executions within a transaction. Therefore if an exception is thrown, all changes
     * will automatically be rolled back.
     *
     * @param database         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        dropAllTables(database);
        onCreate(database);
        Log.i(LOGTAG, "DB Version upgraded" + oldVersion + " to " + newVersion);
    }


    /**
     * @param database the db whose tables to drop
     */
    public void dropAllTables(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS " + HabitContract.DogWalkingTable.TABLE_DOGWALKING);
        database.execSQL("DROP TABLE IF EXISTS " + HabitContract.WalkingHabitTable.TABLE_STEPS);
    }

    /**
     * Deletes all the entries while leaving the Tables
     */
    public void deleteAllEntries() {
        String deleteDogwalkingTable = "DELETE FROM " + HabitContract.DogWalkingTable.TABLE_DOGWALKING;
        String deleteWalkingHabitTable = "DELETE FROM " + HabitContract.WalkingHabitTable.TABLE_STEPS;
        mSQLiteDatabase.execSQL(deleteDogwalkingTable);
        mSQLiteDatabase.execSQL(deleteWalkingHabitTable);
    }

    /**
     * @param duration  - Dog Waliking duration
     * @param description - Desc about the walkk
     * @return the generated id, in case you want to save it
     */
    public long insertDogWalk(int duration, String description) {
        ContentValues values = new ContentValues();
        values.put(HabitContract.DogWalkingTable.COLUMN_DURATION, duration);
        values.put(HabitContract.DogWalkingTable.COLUMN_DESC, description);

        long generatedId = mSQLiteDatabase.insert(HabitContract.DogWalkingTable.TABLE_DOGWALKING, null, values);
        Log.i(LOGTAG, "Successfully inserted to DogWalkingTable");

        return generatedId;
    }

    /**
     * @return a cursor to all the columns of the DogWalkingTable table
     */
    public Cursor read() {
        Cursor cursor = mSQLiteDatabase.query(HabitContract.DogWalkingTable.TABLE_DOGWALKING,   //table name
                                              HabitContract.DogWalkingTable.allColumns,         //Columns to return
                                              null,                                             // column for WHERE CLAUSE
                                              null,                                             // column values for WHERE CLAUSE
                                              null,                                             // grouping the rows
                                              null,                                             //  fllter rows  by grouping
                                              null);                                            //sort order
        Log.i(LOGTAG, "Number of rows returned: " + cursor.getCount());

        return cursor;
    }

    /**
     * @param id  - ID for the row you want to update
     * @param duration New duration
     */

    public void updateDogDuration(long id, int duration) {
        String query = "UPDATE " +
                HabitContract.DogWalkingTable.TABLE_DOGWALKING +
                " Set " +
                HabitContract.DogWalkingTable.COLUMN_DURATION +
                " = " +
                duration +
                " WHERE " +
                HabitContract.DogWalkingTable.COLUMN_ID +
                " = " +
                id;
        Log.i(LOGTAG, "Duration for row id " + id + " set to " + duration);
    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase(HabitContract.DATABASE_NAME);
    }
}