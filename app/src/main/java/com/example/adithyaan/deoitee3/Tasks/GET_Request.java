package com.example.adithyaan.deoitee3.Tasks;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.adithyaan.deoitee3.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GET_Request extends AsyncTask<String, Void, String> {


    String result;

    Context context;

    public GET_Request(Context context) {
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

           Constants.Location.clear();

//            for (JsonObject result: jsonArray.)
            if(!(jsonArray.length() < 1)) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Constants.Location.add(jsonArray.getJSONObject(i).getString("label"));
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

    public  String send(String to,  String body) {
        try {

            final String apiKey = "AAAAnMfNm6g:APA91bHdqBRtEEoGcQVrcpaj3WU8QahnbqA4DMY81TktXpDmSS3C1n5valVXEp5v4kU0mJTSqMZ4RY0ZWz92qtaJhvL8qLBx26MrkbS5SqThnmiqslLGjS__kT6ZrNyrozbXlSXE9uOA";
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key=" + apiKey);
            conn.setDoOutput(true);
            JSONObject message = new JSONObject();
            message.put("to", "ffKxMmvSXTA:APA91bE7O2b8uTBNhOOL5pg9BQaQrbCCCnAjH3VJ9Ghw2-1v5lU3i8FHvazz57WdcbgwGSRvy2Oojurs5tP3ll-y49l1z_omeJP03CEo8i0sZJJz0yfCj2VWsIDWt3c72LQ43co7jH0h");
            message.put("priority", "high");

            JSONObject notification = new JSONObject();
            // notification.put("title", title);
            notification.put("body", "hai");
            message.put("data", notification);
            OutputStream os = conn.getOutputStream();
            os.write(message.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + message.toString());
            System.out.println("Response Code : " + responseCode);
            System.out.println("Response Code : " + conn.getResponseMessage());

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e.getStackTrace());
        }
        return "error";
    }
}