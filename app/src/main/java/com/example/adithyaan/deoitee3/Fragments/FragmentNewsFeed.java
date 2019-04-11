package com.example.adithyaan.deoitee3.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adithyaan.deoitee3.Activities.SignInActivity;
import com.example.adithyaan.deoitee3.Adapters.LocationAdapter;
import com.example.adithyaan.deoitee3.Adapters.NewsFeedAdapter;
import com.example.adithyaan.deoitee3.Constants;
import com.example.adithyaan.deoitee3.Data.NewsFeedData;
import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.Tasks.GET_Request;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class FragmentNewsFeed extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    View view;
    EditText to,from,goods_type;
    ArrayAdapter adapter;
    Button post_btn;
    String str_from,str_to,str_goods;
    ListView from_listview,to_listview;
    ArrayList list=new ArrayList();
    ArrayList test=new ArrayList();
    ProgressDialog progressDialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public FragmentNewsFeed() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

        progressDialog=new ProgressDialog(getActivity());
        progressDialog.setMessage("Posting Your Requirement");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Orders");
        from.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    new Request(getActivity()).execute(s.toString());


                    LocationAdapter listAdapter = new LocationAdapter(getContext(), test);

                    from_listview.setVisibility(View.VISIBLE);
                    from_listview.setAdapter(listAdapter);

                } catch (Exception e) {
                    e.printStackTrace();

                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        to.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    new Request(getActivity()).execute(s.toString()).get(1000, TimeUnit.MILLISECONDS);
                    LocationAdapter listAdapter = new LocationAdapter(getActivity(), test);
                    listAdapter.notifyDataSetChanged();
                    to_listview.setVisibility(View.VISIBLE);
                    to_listview.setAdapter(listAdapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        from_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                from.setText(test.get(position).toString());
                from_listview.setVisibility(View.GONE);

            }
        });
        to_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                to.setText(test.get(position).toString());
                to_listview.setVisibility(View.GONE);
            }
        });
        post_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                FirebaseFunctions mFunctions = FirebaseFunctions.getInstance();
                DatabaseReference d = myRef.child(goods_type.getText().toString());
                HashMap map = new HashMap();
                map.put("from_location",from.getText().toString());
                map.put("to_location",to.getText().toString());
                map.put("goods_descp",goods_type.getText().toString());
                map.put("customer_name","");
                HashMap  h = new HashMap();
                h.put("Name","Test");
                h.put("Amount","1000");
                HashMap  h1 = new HashMap();
                h1.put("9949",h);
                map.put("bids",h1);






                d.setValue(map);
               // data.put("goods",goods_type.getText().toString());
                mFunctions.getHttpsCallable("addMessage").call(map).continueWith(new Continuation<HttpsCallableResult, Object>() {

                    @Override
                    public Object then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        String result;
                        if (task.isSuccessful()) {
                            result = task.getResult().getData().toString();
                        }
                        else {
                            Log.e("Error",task.getException().getMessage());
                            result = "Failed";
                        }
                       // Toast.makeText(getActivity(), "Result:"+result, Toast.LENGTH_SHORT).show();
                        return result;
                    }
                });
                from.setText("");
                to.setText("");
                goods_type.setText("");
                Toast.makeText(getActivity(), "Posted Succesfully", Toast.LENGTH_SHORT).show();
                from_listview.setVisibility(View.GONE);
                to_listview.setVisibility(View.GONE);

            }
        });
    }

    public void initView()
    {
        post_btn=view.findViewById(R.id.post_btn);
        recyclerView=view.findViewById(R.id.rv);
        from=view.findViewById(R.id.from_location);
        from_listview=view.findViewById(R.id.from_listview);
        to_listview=view.findViewById(R.id.to_listview);
        to=view.findViewById(R.id.to_location);
        goods_type=view.findViewById(R.id.goods_type);
        post_btn.setOnClickListener(this);


    }

    public void getData()
    {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.post_btn:
                progressDialog.create();
                progressDialog.show();
              str_from=  from.getText().toString();
              str_to=to.getText().toString();
              str_goods=goods_type.getText().toString();
              progressDialog.setMessage("Posting Your Message");



              sleep();

            break;
        }
    }
    public void sleep()
    {
        Thread thread =new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(5000);




                    Snackbar.make(view, "Successfully Your requirement has been posted", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        to.setText("");
        from.setText("");
        goods_type.setText("");
        progressDialog.dismiss();
        to_listview.setVisibility(View.GONE);
        from_listview.setVisibility(View.GONE);
    }

class Request extends AsyncTask<String, Void, String> {


    String result;

    Context context;

    public Request(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        com.example.adithyaan.deoitee3.Constants.Location.clear();
    }

    @Override
    protected void onPostExecute(String s) {
//        super.onPostExecute(s);


        System.out.println("Result = " + result);

        try {


            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("suggestions");

            test.clear();

//            for (JsonObject result: jsonArray.)
            if (!(jsonArray.length() < 1)) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    test.add(jsonArray.getJSONObject(i).getString("label"));
                }
            } else {
                Constants.Location.add("No results");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            String string = strings[0];

            URL url = new URL(com.example.adithyaan.deoitee3.Constants.Url + string);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod(com.example.adithyaan.deoitee3.Constants.REQUEST_METHOD);
            httpURLConnection.setReadTimeout(com.example.adithyaan.deoitee3.Constants.READ_TIMEOUT);
            httpURLConnection.setConnectTimeout(com.example.adithyaan.deoitee3.Constants.CONNECTION_TIMEOUT);

            try {
                httpURLConnection.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            String input_Data;

            while ((input_Data = bufferedReader.readLine()) != null) {
                stringBuilder.append(input_Data);
            }

            bufferedReader.close();
            inputStreamReader.close();

            result = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
}
