package com.example.adithyaan.deoitee3;
import com.example.adithyaan.deoitee3.Data.NewsFeedData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by arunachalam on 10/12/17.
 */

public class Constants {

    public static String Url = "http://autocomplete.geocoder.cit.api.here.com/6.2/suggest.json?app_id=yXlRVXmXDFgGd1YK2lrd&app_code=aaxafQQUNW7FedkYyIAjxw&query=";

 public  static final String REQUEST_METHOD = "GET";

   public  static final int READ_TIMEOUT = 15000;

  public   static final int CONNECTION_TIMEOUT = 15000;

    static final int Handle_Time = 2000;

    static final int Quick_Handle_Time = 1000;

    static final int Enter_Animation = 0;

    static final int Exit_Animation = 0;

    static final String Next_Page = "Next Page";

    static final String []Spinner = {"3", "5", "10", "20"};

//    static int id[] = {1, 2};
//
//    static String title[] = {"Google", "Yahoo"};
//
//    static String message[] = {"Google Webpage", "Yahoo Webpage"};
//
//    static String url[] = {"http://www.google.com", "http://www.yahoo.com"};

    static int array_Length = 0;

    static ArrayList<Integer> ID = new ArrayList<>();

    static ArrayList<String> Title = new ArrayList<>();

    static ArrayList<String> Message = new ArrayList<>();

   public static ArrayList<String> URL = new ArrayList<>();

    static ArrayList<String> Date_Time = new ArrayList<>();

   public static ArrayList<String> Location = new ArrayList<>();

//    static int check = 0;
    public static ArrayList<NewsFeedData> post=new ArrayList<NewsFeedData>();

   public static String LoadUrl;

    static String from;

    static String to;

    static String goods;

}