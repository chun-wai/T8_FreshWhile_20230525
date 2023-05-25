package com.example.t8_blockchain_food_nutrition_freshwhile_20230525;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewRecord extends AppCompatActivity {

    private ListView recordListView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record);

        recordListView = findViewById(R.id.recordListView);
        databaseHelper = new DatabaseHelper(this);

        displayRecords();
    }

    private void displayRecords() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        List<String> recordsList = new ArrayList<>();

        Cursor cursor = db.query("produce", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String produceId = cursor.getString(cursor.getColumnIndex("fresh_produce_id"));
                String category = cursor.getString(cursor.getColumnIndex("fresh_produce_category"));
                String dateOfHandling = cursor.getString(cursor.getColumnIndex("date_of_handling"));
                String locationOfHandling = cursor.getString(cursor.getColumnIndex("location_of_handling"));

                // Format the record string as per your requirement
                String record = "Produce ID: " + produceId + "\nCategory: " + category + "\nDate of Handling: " + dateOfHandling + "\nLocation of Handling: " + locationOfHandling;
                recordsList.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recordsList);
        recordListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }
}
