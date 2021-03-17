package com.example.tanvi.a304projectassembly;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.app.PendingIntent.getActivity;

public class ListAdapter extends RecyclerView.Adapter {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_adapter, parent, false);
        return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ListViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {

        return ListReports.reports.size();
    }

    private static class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView building;
        private TextView roomNumber;
        private TextView status;
        private TextView time;
        private TextView date;
        private TextView details;


        public ListViewHolder(View itemView)
        {
            super(itemView);
            building = (TextView) itemView.findViewById(R.id.building);
            roomNumber = (TextView) itemView.findViewById(R.id.roomNumber);
            status = (TextView) itemView.findViewById(R.id.status);
            time = (TextView) itemView.findViewById(R.id.time);
            date = (TextView) itemView.findViewById(R.id.date);
            details = (TextView) itemView.findViewById(R.id.details);




            itemView.setOnClickListener((View.OnClickListener) this);

        }

        public void bindView(int position)
        {

            building.setText(ListReports.reports.get(position).building);
            roomNumber.setText(ListReports.reports.get(position).room);
            status.setText("Status: "+ListReports.reports.get(position).status);
            time.setText(ListReports.reports.get(position).time);
            date.setText(ListReports.reports.get(position).date);
            details.setText(ListReports.reports.get(position).details);


        }

        public void onClick(View view) {

        }

    }
}
