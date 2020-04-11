package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.MachineFaultDetailResponse;
import com.example.rainbow.databinding.FragmentFaultDetailBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.widget.PhotoGraphWindow;
import com.example.rainbow.util.GsonUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class FaultDetail extends BaseFragment {


    private FragmentFaultDetailBinding binding;
    private String faultImage;
    private String handleImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fault_detail, container, false);
        initData();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        int id = getArguments().getInt("id");
        HttpUtil.getInstance().getMachineFaultDeail(id).subscribe(
                str -> {
                    MachineFaultDetailResponse machineFaultDetailResponse = GsonUtil.fromJson(str, MachineFaultDetailResponse.class);
                    MachineFaultDetailResponse.DataBean data = machineFaultDetailResponse.getData();
                    binding.setData(data);
                    int faultState = data.getFaultState();
                    String[] faultArray = getResources().getStringArray(R.array.faultStateArray);
                    binding.faultState.setText(faultArray[faultState]);
                    faultImage = data.getFaultImage();
                    handleImage = data.getHandleImage();
                    if (TextUtils.isEmpty(faultImage)) {
                        binding.lookPhoto1.setVisibility(View.GONE);
                    }
                    if (TextUtils.isEmpty(handleImage)) {
                        binding.lookPhoto2.setVisibility(View.GONE);
                    }
                }
        );
    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {

        binding.lookPhoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(faultImage)) {
                    String[] pathArray = null;
                    if (faultImage.contains(",")) {
                        pathArray = faultImage.split(",");
                    } else {
                        pathArray = new String[]{faultImage};
                    }
                    PhotoGraphWindow photoGraphWindow = new PhotoGraphWindow(getContext(), pathArray);
                    photoGraphWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                }

            }
        });
        binding.lookPhoto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(handleImage)) {
                    String[] pathArray = null;
                    if (handleImage.contains(",")) {
                        pathArray = handleImage.split(",");
                    } else {
                        pathArray = new String[]{handleImage};
                    }
                    PhotoGraphWindow photoGraphWindow = new PhotoGraphWindow(getContext(), pathArray);
                    photoGraphWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
                }
            }
        });

    }
}
