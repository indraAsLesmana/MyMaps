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

public class MainActivity extends AppCompatActivity{

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_normal:
                    viewPager.setCurrentItem(ViewPagerAdapter.MAP_FRAGMENT);
                    return true;

                case R.id.navigation_satelite:
                    viewPager.setCurrentItem(ViewPagerAdapter.MAP_STREET_FRAGMENT);
                    return true;

                case R.id.navigation_hybrid:

                    return true;
            }

            return false;
        }
    };

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

        setupViewPager(viewPager);
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

        adapter.addFragment(mapFragment); // fragment 0
        adapter.addFragment(mapStreeFragment); // fragment 1
        viewPager.setAdapter(adapter);
    }
}
