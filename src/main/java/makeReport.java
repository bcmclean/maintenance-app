package com.example.tanvi.a304projectassembly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

public class makeReport extends AppCompatActivity {
    //Setting up various buttons, findme button is NOT functional
    private Button submit;
    private Button findme;
    int anon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String studentNumber = getIntent().getExtras().getString("Number");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_report);
        setSpinners();
        Intent anonOrNo = getIntent();
         anon = anonOrNo.getIntExtra("anonReport",0);

        submit=(Button)findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(validateLocation())||!(validateIssue())){ return; }
                else{ submitReport(studentNumber); }
            }
        });

    }
    public void submitReport(String studentNumber){
        //Assembling strings
        String filename = "data.txt";
        //This string is for the displaying userreports format
        String filename2 = "output.txt";

        String report2 = reportInfo2(studentNumber);
        String report = reportInfo();
        //File I/O
        FileOutputStream outputStream;

        try{
            outputStream = openFileOutput(filename, getApplicationContext().MODE_APPEND);
            outputStream.write(report.getBytes());
            outputStream = openFileOutput(filename2, getApplicationContext().MODE_APPEND);
            outputStream.write(report2.getBytes());
            Toast.makeText(this, "Report recorded", Toast.LENGTH_SHORT).show();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        //Clears stack, and starts next activity
        Intent intent = new Intent(this, display.class);
        Bundle loginDetails = new Bundle();
        loginDetails.putString("Number", studentNumber);
        loginDetails.putInt("anonOrNah", anon);
        intent.putExtras(loginDetails);
        //intent.putExtra("anonOrNah",anon);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public String reportInfo(){
        StringBuilder info = new StringBuilder();
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd; hh:mm:ss a");
        String dateToStr = format.format(today);
        //Building info and appending with delimiter
        Spinner buildingspinner = findViewById(R.id.building_spinner);
        String buildingSelected = getResources().getStringArray(R.array.building)[buildingspinner.getSelectedItemPosition()];
        info.append(buildingSelected + " ");

        //Room info
        Spinner roomspinner = findViewById(R.id.room_spinner);
        String roomSelected = getResources().getStringArray(R.array.room)[roomspinner.getSelectedItemPosition()];
        info.append(roomSelected + ";");
        //Adding in the date
        info.append(dateToStr + ";");
        //Issue info
        Spinner issuespinner = findViewById(R.id.issue_spinner);
        String issueSelected = getResources().getStringArray(R.array.issues)[issuespinner.getSelectedItemPosition()];
        EditText issueBox = (EditText) findViewById(R.id.issue_editText);
        String issueDescription = issueBox.getText().toString();
        if(issueDescription.isEmpty()){
            issueDescription = " ";
        }

        if(issueSelected.matches("ISSUE")){
            issueSelected = " ";
        }
        info.append(issueSelected  + " " + issueDescription + ";");
        info.append("\n");

        return info.toString();
    }

    public String reportInfo2(String studentNumber){
        StringBuilder info2 = new StringBuilder();
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd; hh:mm:ss a");
        String dateToStr = format.format(today);
        //Building info and appending with delimiter
        Spinner buildingspinner = findViewById(R.id.building_spinner);
        String buildingSelected = getResources().getStringArray(R.array.building)[buildingspinner.getSelectedItemPosition()];
        info2.append(buildingSelected + ";");

        //Room info
        Spinner roomspinner = findViewById(R.id.room_spinner);
        String roomSelected = getResources().getStringArray(R.array.room)[roomspinner.getSelectedItemPosition()];
        info2.append(roomSelected + ";");


        //Issue info
        Spinner issuespinner = findViewById(R.id.issue_spinner);
        String issueSelected = getResources().getStringArray(R.array.issues)[issuespinner.getSelectedItemPosition()];
        EditText issueBox = (EditText) findViewById(R.id.issue_editText);
        String issueDescription = issueBox.getText().toString();
        if(issueDescription.isEmpty()){
            issueDescription = " ";
        }

        if(issueSelected.matches("ISSUE")){
            issueSelected = " ";
        }
        info2.append(issueSelected  + " " + issueDescription + ";");

        //Adding in the date
        info2.append(dateToStr + ";");
        info2.append("Received" + ";" + studentNumber +";");
        info2.append("\n");
        return info2.toString();
    }

    private boolean validateIssue(){
        //Setting issue spinner and issue description box
        Spinner issuespinner = findViewById(R.id.issue_spinner);
        String issueSelected = getResources().getStringArray(R.array.issues)[issuespinner.getSelectedItemPosition()];
        EditText issueBox = (EditText) findViewById(R.id.issue_editText);
        String issueDescription = issueBox.getText().toString();
        //If the spinner is not selected and the issue box is empty make it return false
        if(issueSelected.matches("ISSUE")& issueDescription.matches("")){
            Toast.makeText(this, "You must either describe your issue or select one from the dropdown menu", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{ return true; }
    }
    private boolean validateLocation(){
        //Setting up all spinners again
        Spinner buildingspinner = findViewById(R.id.building_spinner);
        Spinner roomspinner = findViewById(R.id.room_spinner);
        String buildingSelected = getResources().getStringArray(R.array.building)[buildingspinner.getSelectedItemPosition()];
        String roomSelected = getResources().getStringArray(R.array.room)[roomspinner.getSelectedItemPosition()];

        //If either of the location spinners are not selected return false and display toast
        if(buildingSelected.matches("BUILDING") || roomSelected.matches("ROOM")){
            Toast.makeText(this, "You must select a location", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{return true;}

    }
    //function that sets all spinners to their respective adapters
    public void setSpinners(){
        //Setting up all of the spinners (building, roomnumber, issues)
        Spinner buildingspinner = findViewById(R.id.building_spinner);
        Spinner roomspinner = findViewById(R.id.room_spinner);
        Spinner issuespinner = findViewById(R.id.issue_spinner);
        //Setting up adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.building, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.room, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.issues, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Mating adapter to all spinners
        buildingspinner.setAdapter(adapter);
        roomspinner.setAdapter(adapter2);
        issuespinner.setAdapter(adapter3);
    }
}