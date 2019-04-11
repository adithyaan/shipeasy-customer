package com.example.adithyaan.deoitee3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.Data.ResponseData;

import java.util.ArrayList;

/**
 * Created by ADITHYA AN on 03-05-2018.
 */
public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList list;
    LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.item,viewGroup,false);

        TextView driver_name=view.findViewById(R.id.drivername);
        TextView amt=view.findViewById(R.id.amount);
        RatingBar ratingBar=view.findViewById(R.id.ratingbar);
        ResponseData data= (ResponseData) list.get(i);
        driver_name.setText(data.getDriver_name());
        amt.setText(data.getAmt());
        ratingBar.setRating(Float.parseFloat(data.getRating()));

        final View finalView = view;
        view.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TextView tv= finalView.findViewById(R.id.confirm);
                tv.setText("Confirmed");
            }
        });
        return view;
    }
}
