package com.example.adithyaan.deoitee3.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adithyaan.deoitee3.Activities.MainActivity;
import com.example.adithyaan.deoitee3.R;

/**

 */
public class FragmentSignUp extends Fragment implements View.OnClickListener {
View view;
Button signup_btn;
ProgressDialog progressDialog;
    public FragmentSignUp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_sign_up, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        progressDialog=new ProgressDialog(getActivity());
        signup_btn.setOnClickListener(this);
    }

    public void initView()
      {
          signup_btn=view.findViewById(R.id.signup);

      }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
         switch(v.getId())
         {
             case R.id.signup:
                 sleep();

         }
    }

    public void sleep()
    {
        progressDialog.setMessage("Creating Account");
        progressDialog.show();
        Thread thread =new Thread()
        {
            @Override
            public void run() {
                try {


                    sleep(2000);

                    startActivity(new Intent(getActivity(), MainActivity.class));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
