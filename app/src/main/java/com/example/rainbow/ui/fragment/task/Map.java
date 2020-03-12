package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.databinding.FragmentMapBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class Map extends BaseFragment implements OnMapReadyCallback {

    private FragmentMapBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.onResume();
        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int errorCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this.getActivity());
        if (ConnectionResult.SUCCESS != errorCode) {
            GooglePlayServicesUtil.getErrorDialog(errorCode,
                    this.getActivity(), 0).show();
        } else {
            binding.mapView.getMapAsync(this);
        }
        initView();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {


    }

    @Override
    public void initlisten() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {}



}
