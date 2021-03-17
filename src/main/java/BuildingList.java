package com.example.tanvi.a304projectassembly;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class BuildingList extends AppCompatActivity {



    public void refresh (){
        final ArrayList<String[]> ADM_reports = new ArrayList<String[]>();
        final ArrayList<String[]> ARTS_reports = new ArrayList<String[]>();
        final ArrayList<String[]> ASC_reports = new ArrayList<String[]>();
        final ArrayList<String[]> CCS_reports = new ArrayList<String[]>();
        final ArrayList<String[]> EME_reports = new ArrayList<String[]>();
        final ArrayList<String[]> FIP_reports = new ArrayList<String[]>();
        final ArrayList<String[]> LIB_reports = new ArrayList<String[]>();
        final ArrayList<String[]> RHC_reports = new ArrayList<String[]>();
        final ArrayList<String[]> SCI_reports = new ArrayList<String[]>();
        final ArrayList<String[]> UNC_reports = new ArrayList<String[]>();

        try {
            String fileName = "data.txt";
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader read = new BufferedReader(isr);

            //ADM ARTS ASC CCS EME FIP LIB RHC UNC
            read.mark(900000000);
            int len = 0;
            while (read.readLine() != null) len++;

            read.reset();

            for (int i = 0; i < len; i++) {
                String[] temp = read.readLine().split(";");



                String x = temp[0].split(" ")[0];


                if(x.equals("ADM"))
                    ADM_reports.add(temp);
                else if (x.equals("ARTS"))
                    ARTS_reports.add(temp);
                else if(x.equals("ASC"))
                    ASC_reports.add(temp);
                else if(x.equals("CCS"))
                    CCS_reports.add(temp);
                else if (x.equals("EME"))
                    EME_reports.add(temp);
                else if(x.equals("FIP"))
                    FIP_reports.add(temp);
                else if(x.equals("LIB"))
                    LIB_reports.add(temp);
                else if(x.equals("RHC"))
                    RHC_reports.add(temp);
                else if (x.equals("SCI"))
                    SCI_reports.add(temp);
                else if (x.equals("UNC"))
                    UNC_reports.add(temp);
            }

        } catch (Exception e) {

        }

        Button logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intents = new Intent(BuildingList.this, WelcomePage.class);
                intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intents);
                finish();
            }

        });

        TextView ADMnum = findViewById(R.id.ADMnum);
        TextView ARTSnum = findViewById(R.id.ARTSnum);
        TextView ASCnum = findViewById(R.id.ASCnum);
        TextView CCSnum = findViewById(R.id.CCSnum);
        TextView EMEnum = findViewById(R.id.EMEnum);
        TextView FIPnum = findViewById(R.id.FIPnum);
        TextView LIBnum = findViewById(R.id.LIBnum);
        TextView RHCnum = findViewById(R.id.RHCnum);
        TextView SCInum = findViewById(R.id.SCInum);
        TextView UNCnum = findViewById(R.id.UNCnum);

        ADMnum.setText(String.valueOf(ADM_reports.size()));
        ARTSnum.setText(String.valueOf(ARTS_reports.size()));
        ASCnum.setText(String.valueOf(ASC_reports.size()));
        CCSnum.setText(String.valueOf(CCS_reports.size()));
        EMEnum.setText(String.valueOf(EME_reports.size()));
        FIPnum.setText(String.valueOf(FIP_reports.size()));
        LIBnum.setText(String.valueOf(LIB_reports.size()));
        RHCnum.setText(String.valueOf(RHC_reports.size()));
        SCInum.setText(String.valueOf(SCI_reports.size()));
        UNCnum.setText(String.valueOf(UNC_reports.size()));



        final ImageButton ADM = findViewById(R.id.ADMbtn);

        ADM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[ADM_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.ADMrooms);


                if(ll.getChildCount() == 0){

                    ADM.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < ADM_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(ADM_reports.get(i)[0].split(" ")[1] + ADM_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                Bundle sending = new Bundle();

                                sending.putString("roomnum", ADM_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }

                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    ADM.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }



            }
        });


        final ImageButton ARTS = findViewById(R.id.ARTSbtn);

        ARTS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[ARTS_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.ARTSrooms);


                if(ll.getChildCount() == 0){

                    ARTS.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < ARTS_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(ARTS_reports.get(i)[0].split(" ")[1] + ARTS_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", ARTS_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    ARTS.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton ASC = findViewById(R.id.ASCbtn);

        ASC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[ASC_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.ASCrooms);


                if(ll.getChildCount() == 0){

                    ASC.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < ASC_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(ASC_reports.get(i)[0].split(" ")[1] + ASC_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", ASC_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    ASC.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });
        final ImageButton CCS = findViewById(R.id.CCSbtn);

        CCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[CCS_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.CCSrooms);


                if(ll.getChildCount() == 0){

                    CCS.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < CCS_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(CCS_reports.get(i)[0].split(" ")[1] + CCS_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", CCS_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    CCS.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton EME = findViewById(R.id.EMEbtn);

        EME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[EME_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.EMErooms);


                if(ll.getChildCount() == 0){

                    EME.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < EME_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(EME_reports.get(i)[0].split(" ")[1] + EME_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", EME_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    EME.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton FIP = findViewById(R.id.FIPbtn);

        FIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[FIP_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.FIProoms);


                if(ll.getChildCount() == 0){

                    FIP.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < FIP_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(FIP_reports.get(i)[0].split(" ")[1] + FIP_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", FIP_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    FIP.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton LIB = findViewById(R.id.LIBbtn);

        LIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[LIB_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.LIBrooms);


                if(ll.getChildCount() == 0){

                    LIB.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < LIB_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(LIB_reports.get(i)[0].split(" ")[1] + LIB_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", LIB_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                finish();
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    LIB.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton RHC = findViewById(R.id.RHCbtn);

        RHC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[RHC_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.RHCrooms);


                if(ll.getChildCount() == 0){

                    RHC.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < RHC_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(RHC_reports.get(i)[0].split(" ")[1] + RHC_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", RHC_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    RHC.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton SCI = findViewById(R.id.SCIbtn);

        SCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[SCI_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.SCIrooms);


                if(ll.getChildCount() == 0){

                    SCI.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < SCI_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(SCI_reports.get(i)[0].split(" ")[1] + SCI_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", SCI_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    SCI.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });

        final ImageButton UNC = findViewById(R.id.UNCbtn);

        UNC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button[] rooms = new Button[UNC_reports.size()];
                for(int i = 0; i < rooms.length; i++){
                    rooms[i] = new Button(BuildingList.this);
                }
                LinearLayout ll = findViewById(R.id.UNCrooms);


                if(ll.getChildCount() == 0){

                    UNC.setBackground(getDrawable(R.drawable.up_arrow));

                    for (int i = 0; i < UNC_reports.size(); i++) {

                        final int index = i;
                        rooms[i].setText(UNC_reports.get(i)[0].split(" ")[1] + UNC_reports.get(i)[1]);

                        rooms[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Bundle sending = new Bundle();

                                sending.putString("roomnum", UNC_reports.get(index)[0]);
                                Intent intent = new Intent(BuildingList.this, roomDetails.class);
                                intent.putExtras(sending);
                                startActivity(intent);
                            }
                        });

                    }


                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    for (int i = 0; i < rooms.length; i++) {
                        ll.addView(rooms[i], lp);
                    }

                    if(ll.getChildCount() == 0){
                        TextView text = new TextView(BuildingList.this);
                        text.setText("No active issues");
                        ll.addView(text);
                    }

                } else {
                    UNC.setBackground(getDrawable(R.drawable.down_arrow));
                    ll.removeAllViews();

                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_list);

        refresh();

    }

    @Override
    protected void onResume() {
        super.onResume();

        refresh();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        refresh();
    }

    public void writeOverFile()
    {

    }
}