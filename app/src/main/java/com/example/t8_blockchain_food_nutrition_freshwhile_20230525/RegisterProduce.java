package com.example.t8_blockchain_food_nutrition_freshwhile_20230525;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.t8_blockchain_food_nutrition_freshwhile_20230525.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterProduce extends AppCompatActivity {

    private EditText freshProduceIdEditText;
    private RadioGroup freshProduceCategoryRadioGroup;
    private EditText dateOfHandlingEditText;
    private EditText locationOfHandlingEditText;
    private Button registerButton;

    private DatabaseHelper databaseHelper;

    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_produce);

        databaseHelper = new DatabaseHelper(this);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        freshProduceIdEditText = findViewById(R.id.freshProduceIdEditText);
        freshProduceCategoryRadioGroup = findViewById(R.id.freshProduceCategoryRadioGroup);
        dateOfHandlingEditText = findViewById(R.id.dateOfHandlingEditText);
        locationOfHandlingEditText = findViewById(R.id.locationOfHandlingEditText);
        registerButton = findViewById(R.id.registerButton);

        dateOfHandlingEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataToDatabase();
            }
        });
    }

    private void saveDataToDatabase() {
        String produceId = freshProduceIdEditText.getText().toString();
        String category = getSelectedRadioButtonText();
        String dateOfHandling = dateOfHandlingEditText.getText().toString();
        String locationOfHandling = locationOfHandlingEditText.getText().toString();

        // Insert the data into the database
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fresh_produce_id", produceId);
        values.put("fresh_produce_category", category);
        values.put("date_of_handling", dateOfHandling);
        values.put("location_of_handling", locationOfHandling);
        long newRowId = db.insert("produce", null, values);
        db.close();

        if (newRowId != -1) {
            // Data insertion successful
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            // Clear the input fields after successful insertion
            clearInputFields();

            // Start HomeActivity
            Intent intent = new Intent(RegisterProduce.this, HomeActivity.class);
            startActivity(intent);
            finish(); // Optional: Close the current activity if needed
        } else {
            // Data insertion failed
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
        }
    }

    private String getSelectedRadioButtonText() {
        int selectedRadioButtonId = freshProduceCategoryRadioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        return selectedRadioButton != null ? selectedRadioButton.getText().toString() : "";
    }

    private void clearInputFields() {
        freshProduceIdEditText.setText("");
        freshProduceCategoryRadioGroup.clearCheck();
        dateOfHandlingEditText.setText("");
        locationOfHandlingEditText.setText("");
    }

    private void showDatePickerDialog() {
        Calendar newCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                dateOfHandlingEditText.setText(dateFormatter.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
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
