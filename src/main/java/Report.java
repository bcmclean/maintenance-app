package com.example.tanvi.a304projectassembly;

import android.support.annotation.NonNull;

public class Report{

    // Building, Room, Details, Date, Time, Status, Reporter
    String building;
    String room;
    String details;
    String date;
    String time;
    String status;
    String reporter;

    Report(String building, String room, String details, String date, String time, String status, String reporter)
    {
        this.building = building;
        this.room = room;
        this.details = details;
        this.date = date;
        this.time = time;
        this.status = status;
        this.reporter = reporter;
    }

}
