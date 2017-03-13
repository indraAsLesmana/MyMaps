package id.co.blogspot.tutor93.mymaps.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import id.co.blogspot.tutor93.mymaps.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback{


    private boolean mapReady;
    private GoogleMap m_map;
    private static final int ANIMATE_TIME = 10000; //10 seconds

    //map latLang
    private LatLng mNew_York = new LatLng(40.7484, -73.9857); //new york
    private LatLng mCibodas = new LatLng(-7.011146, 107.764331); //cibodas
    private LatLng mPatrol = new LatLng(-7.006853, 107.763489); //patrol
    private LatLng mKutes = new LatLng(-7.007298, 107.758508); //kutes
    private LatLng mRancaNyiruan = new LatLng(-7.018636, 107.759854); //nyiru

    private MarkerOptions cibodasPosition, newYorkPosition;
    private Marker markerCibodas;


    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        /**
         * make initial success call it with
         * this.getChildFragmentManager()
         * */
        SupportMapFragment mapFragment = (SupportMapFragment)
                this.getChildFragmentManager().findFragmentById(R.id.map_fragment);

        mapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;

        if (m_map == null){
            return;
        }

        cibodasPosition = new MarkerOptions()
                .position(mCibodas)
                .title("CIBODAS")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_home_red_400_24dp));

        newYorkPosition = new MarkerOptions()
                .position(mNew_York)
                .title("NEW YORK")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.marker));

        markerCibodas = m_map.addMarker(cibodasPosition);
        markerCibodas.showInfoWindow();

        m_map.addMarker(newYorkPosition);


        m_map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                moveCamera(marker.getPosition(), true);
                return true;
            }
        });

        /* m_map.addPolyline(new PolylineOptions().geodesic(true)
        .add(mCibodas)
        .add(mPatrol)
        .add(mKutes)
        .add(mRancaNyiruan)
        .add(mCibodas));*/

        /**
         * // Fill color of the circle
         // 0x represents, this is an hexadecimal code
         // 55 represents percentage of transparency. For 100% transparency, specify 00.
         // For 0% transparency ( ie, opaque ) , specify ff
         // The remaining 6 characters(4d79ff) specify the fill color. contoh :0x554d79ff
         // untuk fillColor pengen ada effect transparansinya.
         // cara kedua dengan Helper Color.
         // Color.argb 64 = tranparansi, 0 = red color, 255 = green color, 0 = blue;
         // untuk pake coba tulis di google search "rgb color picker"
         * */

        m_map.addCircle(new CircleOptions()
                .center(mCibodas)
                .strokeColor(Color.BLUE)
                .fillColor(Color.argb(64, 66, 161, 244))
                .radius(100)); //in meters
    }

    private void moveCamera(LatLng latLng, boolean isAnimate){
        CameraPosition target;

        if (isAnimate) {
            target = CameraPosition.builder()
                    .target(latLng)
                    .bearing(112)
                    .tilt(65)
                    .zoom(17)
                    .build();

            m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), ANIMATE_TIME, null);
        } else {
            // just jump in right into. without animate
            target = CameraPosition.builder()
                    .target(latLng)
                    .zoom(17)
                    .build();
            m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        }
    }
}
