package id.co.blogspot.tutor93.mymaps;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

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
import com.google.android.gms.maps.model.PolylineOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMapLongClickListener{

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_normal:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        moveCamera(mNew_York, true);
                    }
                    return true;
                case R.id.navigation_satelite:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                        moveCamera(mCibodas, true);
                    }

                    return true;
                case R.id.navigation_hybrid:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                        moveCamera(mCibodas, false);
                    }

                    return true;
            }

            return false;
        }
    };

    private GoogleMap m_map;
    private boolean mapReady;
    private LatLng mNew_York = new LatLng(40.7484, -73.9857); //new york
    private LatLng mCibodas = new LatLng(-7.011146, 107.764331); //cibodas
    private LatLng mPatrol = new LatLng(-7.006853, 107.763489); //patrol
    private LatLng mKutes = new LatLng(-7.007298, 107.758508); //kutes
    private LatLng mRancaNyiruan = new LatLng(-7.018636, 107.759854); //nyiru

    private static final int ANIMATE_TIME = 10000; //10 seconds
    private MarkerOptions cibodasPosition, newYorkPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

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


        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
        m_map.addMarker(newYorkPosition);
        m_map.addMarker(cibodasPosition);

        //on marker click...
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

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}
