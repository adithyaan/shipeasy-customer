package com.example.adithyaan.deoitee3.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adithyaan.deoitee3.Adapters.ListAdapter;
import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.Data.ResponseData;

import java.util.ArrayList;


public class FragmentResponse extends Fragment {

    View view;
    ListView listView;
    ArrayList <ResponseData>list=new ArrayList<ResponseData>();
    public FragmentResponse()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fragment_response, container, false);
        initView();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListAdapter adapter=new ListAdapter(getActivity(),list);
        listView.setAdapter(adapter);
    }

    public void initView()
    {
        String arr[]={"Jack","Ramesh","Suresh","Antony"};
        String amt[]={"5000","10000","2000","1000"};
        String rating[]={"5","1","3","5"};
        for (int i=0;i<arr.length;i++)
        {
            list.add(new ResponseData(arr[i],amt[i],rating[i]));

        }
        listView=view.findViewById(R.id.respone_lv);
    }
}
