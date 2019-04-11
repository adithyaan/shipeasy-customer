package com.example.adithyaan.deoitee3.Data;

/**
 * Created by ADITHYA AN on 03-05-2018.
 */

public class ResponseData
{    String driver_name,amt,rating;

    public ResponseData(String driver_name, String amt, String rating) {
        this.driver_name = driver_name;
        this.amt = amt;
        this.rating = rating;
    }


    public String getDriver_name() {
        return driver_name;
    }

    public String getAmt() {
        return amt;
    }

    public String getRating() {
        return rating;
    }
}
