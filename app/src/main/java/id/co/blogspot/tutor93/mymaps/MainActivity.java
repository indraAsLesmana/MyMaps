package id.co.blogspot.tutor93.mymaps;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

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
                    return true;
                case R.id.navigation_satelite:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    }
                    return true;
                case R.id.navigation_hybrid:
                    if (mapReady){
                        m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                    }
                    return true;
            }

            return false;
        }
    };

    private ImageView searchButton_toolbar;
    private EditText textSearch;
    private Toolbar toolbar;
    private GoogleMap m_map;
    private boolean mapReady;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setHomeButtonEnabled(true);
        }

        textSearch = (EditText) findViewById(R.id.textSearch);
        searchButton_toolbar = (ImageView) findViewById(R.id.search_btn);
        toolbar = (Toolbar) findViewById(R.id.toolbar3);



        searchButton_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!textSearch.isShown()){
                    textSearch.setVisibility(View.VISIBLE);
                }else {
                    textSearch.setVisibility(View.INVISIBLE);
                }
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
//        LatLng mCibodas = new LatLng(-7.011146, 107.764331); //cibodas
        LatLng mNew_York = new LatLng(40.7484, -73.9857); //new york
        CameraPosition target = CameraPosition.builder().target(mNew_York).zoom(14).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
