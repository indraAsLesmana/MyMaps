package id.co.blogspot.tutor93.mymaps.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportStreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import id.co.blogspot.tutor93.mymaps.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapStreeFragment extends Fragment
        implements OnStreetViewPanoramaReadyCallback{


    private boolean mapReady;
    private StreetViewPanorama street_map;
//    private StreetViewPanorama map_panorama;

    public MapStreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map_stree, container, false);

        SupportStreetViewPanoramaFragment streetFragment = (SupportStreetViewPanoramaFragment)
                this.getChildFragmentManager().findFragmentById(R.id.fragment_street);

        streetFragment.getStreetViewPanoramaAsync(this);

        return rootView;
    }


    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {
        mapReady = true;
        street_map = streetViewPanorama;

        if (street_map == null){
            return;
        }
        streetViewPanorama.setPosition(new LatLng(-7.011146, 107.764331));

    }
}
