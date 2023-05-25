package com.example.t8_blockchain_food_nutrition_freshwhile_20230525;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                // Perform sign-in logic here (e.g., check credentials in the database)
                if (isValidCredentials(username, password)) {
                    // Sign-in successful, navigate to HomeActivity
                    Intent intent = new Intent(SignIn.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Sign-in failed, display an error message
                    Toast.makeText(SignIn.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // TODO: Implement your own logic to validate the credentials (e.g., check against a database)
        // Return true if the credentials are valid, otherwise return false

        // Replace this with your own validation logic
        if (username.equals("admin") && password.equals("password")) {
            // Credentials are valid
            return true;
        } else {
            // Credentials are invalid
            return false;
        }
    }

}
