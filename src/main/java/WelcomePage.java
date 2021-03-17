package com.example.tanvi.a304projectassembly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {
    Button signIn;
    Button report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInPage();
            }

        });

        report = (Button) findViewById(R.id.report);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reportPage();
            }

        });

    }

    void reportPage()
    {
        int anon = 1;
        Intent intent = new Intent(this, makeReport.class);
        intent.putExtra("anonReport",anon);
        startActivity(intent);
    }

    void signInPage()
    {
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }




}
