package com.example.tanvi.a304projectassembly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    private Button continueButton;
    static String studentUser1 = "12345678";
    static String studentUser2 = "22345678";
    static String maintenanceUser = "00000000";
    static String correctPassword = "password";
    static boolean stayLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText loginName = findViewById(R.id.enterLogin);
        loginName.setText("");



        continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText loginName = (EditText) findViewById(R.id.enterLogin);
                String loginNumber = loginName.getText().toString();
                EditText loginPassword = (EditText) findViewById(R.id.enterPassword);
                String password = loginPassword.getText().toString();
                if (loginNumber.length() != 8)
                {
                    Toast.makeText(SignIn.this, "Invalid login. Login must be 8 numbers.", Toast.LENGTH_SHORT).show();
                }
                // until proper login system is implemented, this system only allows correctPassword variable
                else if (!password.toString().equals(correctPassword))
                {
                    Toast.makeText(SignIn.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                }
                else {
                    continueSigningIn();
                }
            }

        });
    }

    /* once user has entered login details, call report class to read reports from file */
    void continueSigningIn()
    {
        // get details entered
        EditText loginName = (EditText) findViewById(R.id.enterLogin);
        String loginNumber = loginName.getText().toString();

        // not functional currently, but saves whether user wants to stay logged in
        CheckBox keepLoggedIn = findViewById(R.id.keepMeLoggedIn);
        stayLoggedIn = keepLoggedIn.isChecked();

        // if login equals "00000000" then login user is maintenance personnel
        if (loginNumber.equals("00000000"))
        {

            Intent intent = new Intent(this, BuildingList.class);
            finish();
            startActivity(intent);
        }
        // otherwise they are a student user
        else
        {
            Intent intent = new Intent(this, ListReports.class);

            Bundle loginDetails = new Bundle();
            loginDetails.putString("Number", loginNumber);
            intent.putExtras(loginDetails);
            finish();
            startActivity(intent);

        }

    }
}