package com.example.tanvi.a304projectassembly;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class recyclerViewAdaptor extends RecyclerView.Adapter<recyclerViewAdaptor.ViewHolder> {

    private ArrayList<roomDetails.Request> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    public ArrayList<Integer> removeItems = new ArrayList<>();
    private Context mContext;

    recyclerViewAdaptor(Context context, ArrayList<roomDetails.Request> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        roomDetails.Request d = mData.get(position);
        holder.dateTextView.setText(d.sDate);
        holder.timeTextView.setText(d.sTime);
        holder.descTextView.setText(d.desc);

        // Set a click listener for item remove button
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                removeItems.add(position);
                //mData.remove(position);
                //notifyItemRemoved(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView dateTextView;
        TextView timeTextView;
        TextView descTextView;
        private Button mRemoveButton;
        private Button mTestButton;


        ViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);
            timeTextView = itemView.findViewById(R.id.time);
            descTextView = itemView.findViewById(R.id.desc);
            mRemoveButton = itemView.findViewById(R.id.checkBox);
            mTestButton = itemView.findViewById(R.id.markDone);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }

    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void removeItemAtPosition(int position) {
        mData.remove(position);
        notifyItemChanged(position);
    }


    public ArrayList<Integer> getRemoveList() {
        return removeItems;
    }
}
