package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.bean.MachineFaultResponse;
import com.example.rainbow.databinding.FragmentFaultRecordBinding;
import com.example.rainbow.ui.adapter.FaultRecordAdapter;
import com.example.rainbow.ui.main.Task;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class FaultRecord extends BaseFragment {


    private FragmentFaultRecordBinding binding;
    private ArrayList<MachineFaultResponse.DataBean.ItemsBean> datas;
    private FaultRecordAdapter faultRecordAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_fault_record, container, false);
        initView();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    private int jobId;
    private boolean isRepair;
    private boolean isShopSign;

    public void setData(List<MachineFaultResponse.DataBean.ItemsBean> machineFaults, int jobId, Task task, boolean isRepair, boolean isShopSign) {
        this.jobId = jobId;
        this.task = task;
        this.isRepair = isRepair;
        this.isShopSign = isShopSign;
        if (machineFaults != null) {
            datas.addAll(machineFaults);
            faultRecordAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        faultRecordAdapter = new FaultRecordAdapter(getContext(), datas, R.layout.lv_item_fault_record);
        binding.lvFaultRecord.setAdapter(faultRecordAdapter);
    }


    private Task task;

    @Override
    public void initlisten() {

        binding.lvFaultRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MachineFaultResponse.DataBean.ItemsBean bean = datas.get(position);
                if (isRepair) {
                    if (bean.getFaultState() == 0 || bean.getFaultState() == 2) {
                        HandleFault handleFault = new HandleFault();
                        Bundle bundle = new Bundle();
                        bundle.putInt("faultState", bean.getFaultState());
                        bundle.putBoolean("isShopSign",isShopSign);
                        bundle.putInt("id", bean.getId());
                        bundle.putInt("jobId", jobId);
                        handleFault.setArguments(bundle);
                        String title = getString(R.string.scgzcl);
                        task.step2Task("handlerFault", handleFault, " > " + title);
                    } else {
                        FaultDetail faultDetail = new FaultDetail();
                        String title = getContext().getString(R.string.faultDetail);
                        Bundle bundle = new Bundle();
                        bundle.putInt("id", bean.getId());
                        faultDetail.setArguments(bundle);
                        task.step2Task("faultDetail", faultDetail, " > " + title);
                    }
                } else {
                    FaultDetail faultDetail = new FaultDetail();
                    String title = getContext().getString(R.string.faultDetail);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", bean.getId());
                    faultDetail.setArguments(bundle);
                    task.step2Task("faultDetail", faultDetail, " > " + title);
                }

            }
        });

    }
}
