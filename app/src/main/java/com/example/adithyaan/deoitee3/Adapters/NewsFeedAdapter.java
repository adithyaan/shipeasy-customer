package com.example.adithyaan.deoitee3.Adapters;

/**
 * Created by ADITHYA AN on 03-05-2018.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


import com.example.adithyaan.deoitee3.Data.NewsFeedData;
import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.Tasks.InsertTask;


/**
 * Created by ADITHYA AN on 02-05-2018.
 */

public class NewsFeedAdapter extends RecyclerView.Adapter<MyHolder> {

    ArrayList<NewsFeedData> arrayList =new ArrayList<NewsFeedData>();
    Context context;
    String from_location,to_location,customer_name,goods_descp;
    LayoutInflater inflater;

    public NewsFeedAdapter(Context context, ArrayList arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        inflater=LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.news_item,null,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position)
    {
        holder.start_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAmtDialog();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void showAmtDialog()
    {

        AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);
        alertDialog.setTitle("Enter Your Amount to Bid");
        View view=inflater.inflate(R.layout.amt_dialog,null);
        alertDialog.setView(view);
        alertDialog.setPositiveButton("Bid", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                InsertTask insertTask=new InsertTask("url",context);
                //insertTask.execute("");
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
}
class MyHolder extends RecyclerView.ViewHolder
{
    TextView from_location,to_location,customer_name,goods_descp;
    Button start_bid;


    public MyHolder(View itemView)
    {
        super(itemView);
        from_location=itemView.findViewById(R.id.from_location);
        to_location=itemView.findViewById(R.id.to_location);
        customer_name=itemView.findViewById(R.id.customer_name);
        goods_descp=itemView.findViewById(R.id.goods_type);
        start_bid=itemView.findViewById(R.id.payment2);
    }
}
