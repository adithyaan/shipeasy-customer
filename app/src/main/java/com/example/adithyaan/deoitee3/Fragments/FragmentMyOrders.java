package com.example.adithyaan.deoitee3.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adithyaan.deoitee3.Data.NewsFeedData;
import com.example.adithyaan.deoitee3.MainViewHolder;
import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.Service.FireInstanceService;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentMyOrders extends Fragment {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public FragmentMyOrders() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment_my_orders, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv);
        FireInstanceService fi=new FireInstanceService();
        fi.onTokenRefresh();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Orders");
        FirebaseRecyclerAdapter firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<NewsFeedData,MainViewHolder>(NewsFeedData.class,R.layout.news_item,MainViewHolder.class,myRef)
        {
            @Override
            protected void populateViewHolder(MainViewHolder viewHolder, NewsFeedData model, int position)
            {
                viewHolder.inflate(model, getActivity());
            }


        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
