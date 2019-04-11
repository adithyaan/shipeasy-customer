package com.example.adithyaan.deoitee3.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.here.android.mpa.mapping.MapRoute;
import com.here.android.mpa.routing.Route;
import com.here.android.mpa.routing.RouteManager;
import com.here.android.mpa.routing.RouteOptions;
import com.here.android.mpa.routing.RoutePlan;
import com.here.android.mpa.routing.RouteResult;

import java.lang.ref.WeakReference;
import java.util.List;


public class FragmentShowRoute extends Fragment implements RouteManager.Listener{

    private Map map = null;
    private MapFragment mapFragment = null;

    public FragmentShowRoute() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_show_route, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapFragment = (MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.mapfragment1);

        mapFragment.init(new OnEngineInitListener() {
            @Override
            public void onEngineInitializationCompleted(Error error) {
                if (error == Error.NONE) {
                    map = mapFragment.getMap();
                    PositioningManager.OnPositionChangedListener positionChangedListener = new PositioningManager.OnPositionChangedListener() {
                        @Override
                        public void onPositionUpdated(PositioningManager.LocationMethod locationMethod, GeoPosition geoPosition, boolean b) {
                            map.setCenter(new GeoCoordinate(13.0827, 80.2707, 0.0), Map.Animation.NONE);
                        }

                        @Override
                        public void onPositionFixChanged(PositioningManager.LocationMethod locationMethod, PositioningManager.LocationStatus locationStatus) {

                        }
                    };

                    PositioningManager positioningManager = PositioningManager.getInstance();
                    positioningManager.addListener(new WeakReference<PositioningManager.OnPositionChangedListener>(positionChangedListener));
                    positioningManager.start(PositioningManager.LocationMethod.GPS_NETWORK);
                    map.getPositionIndicator().setVisible(true);
                    map.setCenter(new GeoCoordinate(13.0827, 80.2707, 0.0), Map.Animation.LINEAR);
                } else {
                    Toast.makeText(getActivity(), "Cannot initialize map", Toast.LENGTH_LONG).show();
                }
            }
        });

        RouteManager routeManager = new RouteManager();
        RoutePlan routePlan = new RoutePlan();
        routePlan.addWaypoint(new GeoCoordinate(13.0827, 80.2707, 0.0));
        routePlan.addWaypoint(new GeoCoordinate(13.0382, 80.1565, 0.0));
        RouteOptions routeOptions = new RouteOptions();
        routeOptions.setTransportMode(RouteOptions.TransportMode.CAR);
        routeOptions.setRouteType(RouteOptions.Type.FASTEST);
        routePlan.setRouteOptions(routeOptions);
        routeManager.calculateRoute(routePlan, new RouteManager.Listener() {
            @Override
            public void onProgress(int i) {

            }

            @Override
            public void onCalculateRouteFinished(RouteManager.Error error, List<RouteResult> list) {

                if (error == RouteManager.Error.NONE) {
                    // Render the route on the map
                    MapRoute mapRoute = new MapRoute(list.get(0).getRoute());
                    map.addMapObject(mapRoute);




                }
            }
        });
    }

    @Override
    public void onProgress(int i) {

    }

    @Override
    public void onCalculateRouteFinished(RouteManager.Error error, List<RouteResult> list) {

    }

}
