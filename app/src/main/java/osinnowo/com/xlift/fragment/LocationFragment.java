package osinnowo.com.xlift.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import osinnowo.com.xlift.R;
import osinnowo.com.xlift.gps.GPSTracker;
import osinnowo.com.xlift.model.Driver;
import osinnowo.com.xlift.model.Location;
import osinnowo.com.xlift.model.NearbyDriver;
import osinnowo.com.xlift.model.NearbyEndpoint;
import osinnowo.com.xlift.network.ApiClient;
import osinnowo.com.xlift.service.DriverService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationFragment extends Fragment {


    public LocationFragment() {
        // Required empty public constructor
    }


    MapView mMapView;
    private GoogleMap googleMap;
    private MarkerOptions options = new MarkerOptions();
    private ArrayList<LatLng> latlngs = new ArrayList<>();
    private double Lat ;
    private double Lng;
    private View rootView;
    private RelativeLayout mRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_location, container, false);

            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);

            mMapView.onResume(); // needed to get the map to display immediately


            mRoot = (RelativeLayout) rootView.findViewById(R.id.location_view);

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button

                //googleMap.setMyLocationEnabled(true);
                GPSTracker gpsTracker = new GPSTracker(getActivity().getApplicationContext());

                Lat = gpsTracker.getLatitude();
                Lng = gpsTracker.getLongitude();

                DriverService service = ApiClient.getClient().create(DriverService.class);

                SharedPreferences settings = getContext().getSharedPreferences("OAUTH_STORE", 0);

                String header = "Bearer " + settings.getString("token", null);

                Call<NearbyEndpoint> call = service.getData(Double.toString(Lat), Double.toString(Lng), header);

                call.enqueue(new Callback<NearbyEndpoint>() {
                    @Override
                    public void onResponse(Response<NearbyEndpoint> response, Retrofit retrofit) {
                        //Toast.makeText(getActivity().getApplicationContext(), "To String: " + response.body().toString(), Toast.LENGTH_LONG).show();
                        Log.e("bad", "step 1");
                        Log.e("bad", response.raw().toString());

                        NearbyEndpoint rowListItem = response.body();
                        Log.e("bad", "step 2");
                        try{
                            List<NearbyDriver> DriverNearby = rowListItem.nearby_drivers; // failing point
                            Log.e("bad", "step 3");


                            String ty = DriverNearby.get(0).getRideType();
                            Log.e("bad", "step 4");
                            Toast.makeText(getContext(), "Type : " + ty, Toast.LENGTH_LONG).show();
                            Log.e("bad", "step 5");



                            Log.d("Lat", Double.toString(Lat));

                            Log.d("Lng", Double.toString(Lng));

                            Toast.makeText(getActivity().getApplicationContext(),"Lat:" + Double.toString(Lat) + ", Lng: " + Double.toString(Lng), Toast.LENGTH_SHORT).show();

                            // For dropping a marker at a point on the Map
                            LatLng sydney = new LatLng(Lat, Lng);

                            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_timer_auto)));

                            for(NearbyDriver NDrivers : DriverNearby) {
                                for (Driver NDriver : NDrivers.getDrivers()) {
                                    for(Location NLocation : NDriver.getLocations()) {
                                        MarkerOptions options = new MarkerOptions();
                                        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_directions_car));
                                        LatLng latlng = new LatLng(NLocation.getLat(), NLocation.getLng());
                                        options.position(latlng).title("Title").snippet("Driver");
                                        googleMap.addMarker(options);
                                    }
                                }
                            }
                            // For zooming automatically to the location of the marker
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        } catch (Exception e){
                            if(response.code() == 400) {
                                Snackbar.make(mRoot, "The requested location is not inside a Lyft service area", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Snackbar.make(mRoot, "Please enable your GPS", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });


            }


        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

}
