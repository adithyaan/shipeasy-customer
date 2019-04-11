package com.example.adithyaan.deoitee3.Adapters;

/**
 * Created by ADITHYA AN on 03-05-2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adithyaan.deoitee3.R;

import java.util.ArrayList;

/**
 * Created by arunachalam on 28/4/18.
 */

public class LocationAdapter extends BaseAdapter {
    Context context;
    ArrayList list;
    LayoutInflater inflater;

    public LocationAdapter(Context context, ArrayList list) {
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
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.location_item,viewGroup,false);

        TextView placetv=view.findViewById(R.id.place_tv);
        placetv.setText(list.get(i).toString());
        Toast.makeText(context, "ss"+list.get(i).toString(), Toast.LENGTH_SHORT).show();
        return view;
    }
}
