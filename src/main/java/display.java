package com.example.tanvi.a304projectassembly;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
public class display extends AppCompatActivity {
    int reports=0;
    Button done_button;
    int anonOrNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String studentNumber = getIntent().getExtras().getString("Number");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent anonUser = getIntent();
        anonOrNot = getIntent().getExtras().getInt("anonOrNah");

        numOfReports();
        readData();

        done_button = (Button) findViewById(R.id.done_button);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(anonOrNot == 1){

                   Intent backhome = new Intent(display.this, WelcomePage.class);
              backhome.setFlags(backhome.FLAG_ACTIVITY_CLEAR_TOP);
              startActivity(backhome);
              finish();
               }

               else {
                   Intent intent = new Intent(display.this, ListReports.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 Bundle loginDetails = new Bundle();
                  loginDetails.putString("Number", studentNumber);
                  intent.putExtras(loginDetails);
                  // intent.putExtra("Number", studentNumber);
                   startActivity(intent);
                   finish();
               }}
        });
    }

    public void readData(){
        String filename = "data.txt";
        String data = null;
        try{
            FileInputStream fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            for(int i=0; i < reports; i++){
                data=bufferedReader.readLine();
            }
            output(data);
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public void output(String data){
        //Setting textview to display data
        TextView setBuilding = (TextView)findViewById(R.id.buildingInfo);
        TextView setRoom = (TextView)findViewById(R.id.roominfo);
        TextView setIssue = (TextView)findViewById(R.id.issueinfo);
        //Breaking input into string array to easier display data
        String input = data;
        //breaks input based on ';'
        String[] inputs = input.split(";");
        //Room and building
        setBuilding.setText(inputs[0]);
        //Date
        setRoom.setText(inputs[1] + inputs[2]);
        //Issue
        setIssue.setText(inputs[3]);

    }
    /*
     * This function reads the file
     * gets the number of reports and will report back that number
     * the function readData() will use that number to read the most
     * recently added report for this user */
    public Integer numOfReports(){
        String filename = "data.txt";
        try{
            FileInputStream fileInputStream = openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while( bufferedReader.readLine() !=null){reports++;}
        }
        catch(IOException ex){ ex.printStackTrace(); }
        return reports;
    }

}