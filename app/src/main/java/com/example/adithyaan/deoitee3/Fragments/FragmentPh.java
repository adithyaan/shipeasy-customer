package com.example.adithyaan.deoitee3.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adithyaan.deoitee3.Activities.MainActivity;
import com.example.adithyaan.deoitee3.Interfaces.OtpListener;
import com.example.adithyaan.deoitee3.R;
import com.example.adithyaan.deoitee3.Tasks.SendOtp;


public class FragmentPh extends Fragment implements View.OnClickListener{
    EditText ph_et,otp_et;
    TextView get_otp_tv,sub_otp_tv;
    String str_ph,str_otp,str_gen_otp;
    SendOtp sendOtp;
    View view;
    OtpListener otpListener;
    public FragmentPh()
    {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ph, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(); //initialising the componenets present in the view

        getPhoneNumber();


    }

    public void initView()
    {
        ph_et=view.findViewById(R.id.et_ph_num);
        otp_et=view.findViewById(R.id.et_otp);
        sendOtp=new SendOtp(getActivity());
        get_otp_tv=view.findViewById(R.id.proceed_tv);
        sub_otp_tv=view.findViewById(R.id.submit_otp);

        view.findViewById(R.id.skip).setOnClickListener(this);
        get_otp_tv.setOnClickListener(this);
        sub_otp_tv.setOnClickListener(this);
    }

    public boolean getPhoneNumber()
    {
        str_ph=ph_et.getText().toString();

        boolean isValid = false;

        if (Patterns.PHONE.matcher(str_ph).matches())
        {
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("ph_num_pref", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("ph",str_ph);
            editor.commit();
            isValid=true;
            generateOtp();
            sendOtp.SendSms(str_ph,str_gen_otp);
            view.findViewById(R.id.one).setVisibility(View.GONE);
            view.findViewById(R.id.two).setVisibility(View.VISIBLE);

        }
        else
        {
            isValid=false;
        }

        return isValid;


}

    public void verifyOtp()
    {
        str_otp=otp_et.getText().toString().trim();
        if (str_otp.equals(str_gen_otp.trim()))
        {
            otpListener.onOtpSuccess();
        }
        else
        {
            Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
        }

    }

    public void generateOtp()
    {
        str_gen_otp= String.valueOf(sendOtp.getRandomNumber());
        Log.e("otp",str_gen_otp);
    }


    public void setOtpSuccessListener(OtpListener otpListener) {
        this.otpListener = otpListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.proceed_tv:
                Toast.makeText(getActivity(), "asdfas", Toast.LENGTH_SHORT).show();
                getPhoneNumber();
                break;
            case R.id.submit_otp:
                Toast.makeText(getActivity(), "asdf", Toast.LENGTH_SHORT).show();
                verifyOtp();
                break;
            case R.id.skip:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new FragmentSignUp()).commit();
                break;
        }
    }
}
