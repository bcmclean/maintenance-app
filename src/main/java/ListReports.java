package com.example.tanvi.a304projectassembly;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ListReports extends AppCompatActivity {
    static ArrayList<Report> reports = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // clear array list to avoid duplicates
        reports.clear();
        final String studentNumber = getIntent().getExtras().getString("Number");
        readReports(studentNumber);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reports);

        // if user has no previously submitted reports, show different message
        if (reports.size() == 0)
        {
            TextView message = findViewById(R.id.report);
            message.setText("You don't have any previously submitted reports.");
        }

        ListFragment fragment = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder, fragment);
        fragmentTransaction.commit();

        Button submitNewReport = findViewById(R.id.submit);
        submitNewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReport(studentNumber);
            }

        });

        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

    void submitReport(String studentNumber)
    {

        Intent intent = new Intent(this, makeReport.class);

        Bundle loginDetails = new Bundle();
        loginDetails.putString("Number", studentNumber);

        intent.putExtras(loginDetails);
        startActivity(intent);
    }

    /* In case things need to be quickly added to the file for testing
     void writeReports()
    {

        String filename = "output.txt";

        FileOutputStream outputStream;


        try {
            FileInputStream fis = openFileInput(filename);
            if(fis.available()==0) {
                outputStream = openFileOutput(filename, Context.MODE_APPEND);

                String fileContents = "ARTS;203;Spill;October 3 2018;10:30 am;Received;12345678\n"
                        + "SCI;234;Light burnt out;November 1 2018;12:00pm;Fixed;12345678\n";

                outputStream.write(fileContents.getBytes());
                outputStream.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public void readReports(String number)
    {
        String line = null;
        try
        {
            FileInputStream fis = openFileInput("output.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine())!= null)
            {
                String thisRecord = line;

                String building = thisRecord.substring(0, thisRecord.indexOf(";"));
                thisRecord = thisRecord.substring(thisRecord.indexOf(";")+1, thisRecord.length());

                String room = thisRecord.substring(0, thisRecord.indexOf(";"));
                thisRecord = thisRecord.substring(thisRecord.indexOf(";")+1, thisRecord.length());

                String details = thisRecord.substring(0, thisRecord.indexOf(";"));
                thisRecord = thisRecord.substring(thisRecord.indexOf(";")+1, thisRecord.length());

                String date = thisRecord.substring(0, thisRecord.indexOf(";"));
                thisRecord = thisRecord.substring(thisRecord.indexOf(";")+1, thisRecord.length());

                String time = thisRecord.substring(0, thisRecord.indexOf(";"));
                thisRecord = thisRecord.substring(thisRecord.indexOf(";")+1, thisRecord.length());

                String status = thisRecord.substring(0, thisRecord.indexOf(";"));
                thisRecord = thisRecord.substring(thisRecord.indexOf(";")+1, thisRecord.length());

                String reporter = thisRecord.substring(0, thisRecord.length()-1);

                // if the report matches the login number, then add to array list (to display it)
                if (reporter.equals(number)) {
                    reports.add(new Report(building, room, details, date, time, status, reporter));
                }

            }

            // Currently ordered by oldest to newest, reverses order (newest to oldest)
            Collections.reverse(reports);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }




}
