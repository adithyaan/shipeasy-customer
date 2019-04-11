package com.example.adithyaan.deoitee3.Tasks;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by ADITHYA AN on 24-02-2018.
 */

public class SendOtp {

    Context context;
    public SendOtp(Context context)
    {
        this.context=context;
    }
    public void SendSms(final String to, final String message) {



        StringRequest menuRequest = new StringRequest(Request.Method.POST,"https://ruparavulakollu.000webhostapp.com/sms.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("RAVAKOLU",response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        // Snack("Volley " + error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("phone",to);
                params.put("msg",message);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(menuRequest);




    }

    public int getRandomNumber()
    {
        int min = 1111;
        int max = 9999;
        final int random = new Random().nextInt((max - min) + 1) + min;

        return random;
    }

}
