package com.example.adithyaan.deoitee3.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.adithyaan.deoitee3.Fragments.FragmentPh;
import com.example.adithyaan.deoitee3.Fragments.FragmentSignUp;
import com.example.adithyaan.deoitee3.Interfaces.OtpListener;
import com.example.adithyaan.deoitee3.R;

import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity implements OtpListener{
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inflate(); //initialises the first screen

        requestPermissions();
    }

    public void inflate()
    {
        FragmentPh fragmentPh=new FragmentPh();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragmentPh).commit();
        fragmentPh.setOtpSuccessListener(this);
    }


    @Override
    public void onOtpSuccess() {

        getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentSignUp()).commit();
        //startActivity(new Intent(SignInActivity.this,MainActivity.class));
    }

    @Override
    public void onOtpFailed() {

    }
    private void requestPermissions() {

        final List<String> requiredSDKPermissions = new ArrayList<String>();
        requiredSDKPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        requiredSDKPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requiredSDKPermissions.add(Manifest.permission.INTERNET);
        requiredSDKPermissions.add(Manifest.permission.ACCESS_WIFI_STATE);
        requiredSDKPermissions.add(Manifest.permission.ACCESS_NETWORK_STATE);

        ActivityCompat.requestPermissions(this,
                requiredSDKPermissions.toArray(new String[requiredSDKPermissions.size()]),
                REQUEST_CODE_ASK_PERMISSIONS);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                for (int index = 0; index < permissions.length; index++) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {


                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                                permissions[index])) {
                            Toast.makeText(this,
                                    "Required permission " + permissions[index] + " not granted. "
                                            + "Please go to settings and turn on for sample app",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this,
                                    "Required permission " + permissions[index] + " not granted",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }



            }

        }
    }
}
