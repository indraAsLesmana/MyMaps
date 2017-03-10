package id.co.blogspot.tutor93.mymaps;

import android.media.Image;
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

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_favorite:
                    return true;
                case R.id.navigation_profile:
                    return true;
            }

            return false;
        }
    };

    private ImageView searchButton_toolbar;
    private EditText textSearch;
    private Toolbar toolbar;

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
    }

}
