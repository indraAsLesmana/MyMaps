package id.co.blogspot.tutor93.mymaps;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

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

import id.co.blogspot.tutor93.mymaps.Adapter.ViewPagerAdapter;
import id.co.blogspot.tutor93.mymaps.Fragment.MapFragment;
import id.co.blogspot.tutor93.mymaps.Fragment.MapStreeFragment;

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
                    viewPager.setCurrentItem(0);
                    return true;

                case R.id.navigation_satelite:
                    viewPager.setCurrentItem(1);
                    return true;

                case R.id.navigation_hybrid:

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
    private Marker markerCibodas;

    private Toolbar toolbar;
    private DrawerLayout navDrawer;
    private NavigationView navView;
    private ActionBarDrawerToggle toggle;

    private MapFragment mapFragment;
    private MapStreeFragment mapStreeFragment;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null ){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            setUpNavDrawer();
        }

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.main_pagger);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        /*cibodasPosition = new MarkerOptions()
                .position(mCibodas)
                .title("CIBODAS")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.ic_home_red_400_24dp));

        newYorkPosition = new MarkerOptions()
                .position(mNew_York)
                .title("NEW YORK")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.marker));*/


        /*SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);*/

        setupViewPager(viewPager);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;

        /*markerCibodas = m_map.addMarker(cibodasPosition);
        markerCibodas.showInfoWindow();

        m_map.addMarker(newYorkPosition);*/

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

    private void setUpNavDrawer() {
        navDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                navDrawer.closeDrawers();
                onNavigationItemClick(item.getItemId());
                return true;
            }
        });

        toggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                navDrawer,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.open,  /* "open drawer" description */
                R.string.close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View drawer) {
                super.onDrawerClosed(drawer);
                invalidateOptionsMenu();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawer) {
                super.onDrawerOpened(drawer);
                invalidateOptionsMenu();
            }
        };
        navDrawer.addDrawerListener(toggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onNavigationItemClick(int id) {
        /*switch (id) {
            case R.id.nav_item_dash:
                title.setText("Dashboard Activity");
                break;
            case R.id.nav_item_cal:
                title.setText("Calendar Activity");
                break;
            case R.id.nav_item_prefs:
                title.setText("Preference Activity");
                break;
        }*/
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mapFragment = new MapFragment();
        mapStreeFragment = new MapStreeFragment();

        adapter.addFragment(mapFragment);
        adapter.addFragment(mapStreeFragment);
        viewPager.setAdapter(adapter);
    }
}
