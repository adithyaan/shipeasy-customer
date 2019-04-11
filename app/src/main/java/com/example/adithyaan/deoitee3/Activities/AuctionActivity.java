package com.example.adithyaan.deoitee3.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.adithyaan.deoitee3.Data.Data;
import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AuctionActivity extends AppCompatActivity {
RecyclerView recyclerView;
    DatabaseReference myRef;
    FirebaseDatabase database;
    String to,from;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);
        recyclerView=findViewById(R.id.rv);
        String instance=getIntent().getExtras().getString("instance");
        to=getIntent().getExtras().getString("to");
        from=getIntent().getExtras().getString("from");
        TextView totv=findViewById(R.id.to_location);
        totv.setText(to);
        TextView fromtv=findViewById(R.id.from_location);
        fromtv.setText(from);
        TextView descp=findViewById(R.id.good);
        descp.setText(instance);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Orders").child(instance).child("bids");


        FirebaseRecyclerAdapter firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Data,ViewHolder>(Data.class,R.layout.firebase,ViewHolder.class,myRef)
        {
            @Override
            protected void populateViewHolder(ViewHolder viewHolder, Data model, int position)
            {
                viewHolder.inflate(model);
            }


        };
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }
}
