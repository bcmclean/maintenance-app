package com.example.tanvi.a304projectassembly;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ListFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_list_fragment, container, false);
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.listRecyclerView);
        ListAdapter listAdapter = new ListAdapter();
        rv.setAdapter(listAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToSummary();
            }
        });
        return view;
    }

    public void goToSummary()
    {
        Intent intent = new Intent(getActivity(), display.class);
        startActivity(intent);
    }


}