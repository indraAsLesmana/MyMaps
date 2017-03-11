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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_normal:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    }
                    moveCamera(mNew_York);
                    return true;
                case R.id.navigation_satelite:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    }
                    moveCamera(mCibodas);

                    return true;
                case R.id.navigation_hybrid:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    }
                    moveCamera(mNew_York);
                    return true;
            }

            return false;
        }
    };

    private GoogleMap m_map;
    private boolean mapReady;
    private LatLng mNew_York = new LatLng(40.7484, -73.9857);
    private LatLng mCibodas = new LatLng(-7.011146, 107.764331); //cibodas
    private static final int ANIMATE_TIME = 5000; // 5 second




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;

    }

    private void moveCamera(LatLng latLng){
        CameraPosition target = CameraPosition.builder()
                .target(latLng)
                .bearing(112)
                .tilt(65)
                .zoom(17)
                .build();

//        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target)); // just jump in right into. without animate
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), ANIMATE_TIME, null);
    }
}
