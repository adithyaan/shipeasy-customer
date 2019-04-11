package com.example.adithyaan.deoitee3.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adithyaan.deoitee3.R;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.GeoPosition;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.PositioningManager;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteResult;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;



public class FragmentHome extends Fragment implements RouteManager.Listener {
    View view;
    ArrayList list=new ArrayList();


    private Map map = null;
    private MapFragment mapFragment = null;
    public FragmentHome()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view = inflater.inflate(R.layout.fragment_home, container, false);


      return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.mapfragment);


        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(Error error) {
                if (error == Error.NONE) {
                    map = mapFragment.getMap();


                    PositioningManager.OnPositionChangedListener positionChangedListener = new PositioningManager.OnPositionChangedListener() {
                        @Override
                        public void onPositionUpdated(PositioningManager.LocationMethod locationMethod, GeoPosition geoPosition, boolean b) {


                            map.setCenter( new GeoCoordinate(geoPosition.getCoordinate().getLatitude(),geoPosition.getCoordinate().getLongitude()), Map.Animation.NONE);

                            Log.e("current", String.valueOf((geoPosition.getCoordinate().getLatitude()+geoPosition.getCoordinate().getLongitude())));
                        }

                        @Override
                        public void onPositionFixChanged(PositioningManager.LocationMethod locationMethod, PositioningManager.LocationStatus locationStatus) {

                        }
                    };

                    PositioningManager positioningManager = PositioningManager.getInstance();
                    positioningManager.addListener(new WeakReference<PositioningManager.OnPositionChangedListener>(positionChangedListener));
                    positioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
                    GeoPosition geoPosition= positioningManager.getPosition();
                    map.getPositionIndicator().setVisible(true);

                    map.setCenter(new GeoCoordinate(19.146879, 73.007631,0), Map.Animation.NONE);
                } else

                {
                    Toast.makeText(getActivity(), "Cannot initialize map"+error.getDetails(), Toast.LENGTH_LONG).show();
                    Log.e("error",error.getDetails()+error.getStackTrace());
                }
            }
        });

    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onCalculateRouteFinished(RouteManager.Error error, List<RouteResult> list)
    {

    }
    }



