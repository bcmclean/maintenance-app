package com.example.tanvi.a304projectassembly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class roomDetails extends AppCompatActivity {

    recyclerViewAdaptor adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        String file = "data.txt";
        String line;
        String[] data;
        Request r;
        int count = 0;

        Bundle recieving = getIntent().getExtras();

        String roomnum = recieving.getString("roomnum");

        final ArrayList<Request> requests = new ArrayList<>();
        try{
            FileInputStream fis = openFileInput(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while((line = br.readLine()) != null){
                data = line.split(";");
                r = new Request();
                r.rNum = data[0];
                r.sDate = data[1];
                r.sTime = data[2];
                r.desc = data[3];


                if(r.rNum.equals(roomnum)) {
                    requests.add(count, r);
                    count++;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new recyclerViewAdaptor(this, requests);
        recyclerView.setAdapter(adapter);

        Request title = requests.get(0);
        TextView roomNum = findViewById(R.id.roomNum);
        roomNum.setText(title.rNum);


        Button setNext = findViewById(R.id.markDone);
        setNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> rem;
                rem = adapter.getRemoveList();

                if (rem.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "No requests selected.",
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {

                    int i = rem.get(0);
                    if (rem.size() == 1) {
                        requests.remove(i);
                        adapter.notifyDataSetChanged();
                        rem.clear();

                        CheckBox checkBox = findViewById(R.id.checkBox);
                        if (checkBox.isChecked()) {
                            checkBox.toggle();
                        }

                        Toast toast = Toast.makeText(getApplicationContext(),
                                "Request removed.",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (rem.size() >= 2) {
                        Collections.sort(rem, Collections.reverseOrder());

                        for (int j : rem)
                            adapter.removeItemAtPosition(j);

                        int s = rem.size();
                        Toast toast = Toast.makeText(getApplicationContext(),
                                s + " requests removed.",
                                Toast.LENGTH_SHORT);
                        toast.show();

                    }
                }
                toBuildingList();
            }
        });

    }

    public void toBuildingList()
    {
        Intent intent = new Intent(this, BuildingList.class);
        finish();
        startActivity(intent);
    }

    public static class Request {
        public String rNum;
        public String sDate;
        public String sTime;
        public String desc;

    }

}
