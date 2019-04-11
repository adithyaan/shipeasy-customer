package com.example.adithyaan.deoitee3.Tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by ADITHYA AN on 03-05-2018.
 */

public class InsertTask extends AsyncTask<String,Void,String> 
{
    String url;
   Context context;

   ProgressDialog progressDialog=new ProgressDialog(context);

    public InsertTask(String url, Context context) {
        this.url = url;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        insertData();
        return null;
    }

    
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
        progressDialog.setMessage("Please Wait");
    }
    
    public void insertData()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
           
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
          
            @Override
            public void onErrorResponse(VolleyError error) {
                
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
