package io.github.ynagarjuna1995.udacityhabittracker;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import io.github.ynagarjuna1995.udacityhabittracker.util.HabitDBOpenHelper;

public class HabitActivity extends AppCompatActivity {

    private static final String LOGTAG = "HT Launcher Activity";
    private HabitDBOpenHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        DBHelper = new HabitDBOpenHelper(this);

    }
        /*
        Code to check that insert, delete methods work
        the read method Logs the number of rows.
         */
    public void insertTestData() {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        Log.i(LOGTAG, "Database now created.");
        long rowId_1 = DBHelper.insertDogWalk(20, "Krish walked the dolly in park ");
        DBHelper.insertDogWalk(5, "No Entry in park in the evening  ");
        DBHelper.insertDogWalk(35, "Dolly visting the pet store of for her food..She liked black dog very much there");
        /*REad method hard coded for DogWalkingTable*/
        DBHelper.read();

        db.close();
        Log.i(LOGTAG, "Database now closed- scope: Insert Test Data");
    }

    public void deleteTestData(){
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        DBHelper.deleteAllEntries();
        DBHelper.read();
        db.close();
        Log.i(LOGTAG, "Database now closed- scope: Insert Delete test Data");
    }

    public void DeleteDatabase(){
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        DBHelper.deleteAllEntries();
        DBHelper.read();
        /*This Removes the Database file under the data folder*/
        DBHelper.deleteDatabase(this);
        Log.i(LOGTAG, "Database has been deleted");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.insertTestData:
                insertTestData();
                break;
            case R.id.deleteTestData:
                deleteTestData();
                break;
            case R.id.deleteTestDatabase:
                DeleteDatabase();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}