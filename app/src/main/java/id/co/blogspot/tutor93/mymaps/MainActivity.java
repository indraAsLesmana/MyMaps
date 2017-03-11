package id.co.blogspot.tutor93.mymaps;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

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
                .title("CIBODAS");

        newYorkPosition = new MarkerOptions()
                .position(mNew_York)
                .title("NEW YORK");


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
