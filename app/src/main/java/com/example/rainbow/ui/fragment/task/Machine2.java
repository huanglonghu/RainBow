package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.databinding.FragmentMachine2Binding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.MyPageAdapter;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class Machine2 extends BaseFragment {

    private FragmentMachine2Binding binding;
    private int id;
    private int machineId;
    private MachineDetailResponse.DataBean data;
    private Task task;
    private FaultRecord faultRecord;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_machine2, container, false);
            binding.setPresenter(Presenter.getInstance());
            initView();
            initlisten();
        }
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        HttpUtil.getInstance().getMachineDetail(id, machineId).subscribe(
                str -> {
                    MachineDetailResponse machineDetailResponse = GsonUtil.fromJson(str, MachineDetailResponse.class);
                    data = machineDetailResponse.getData();
                    binding.setData(data);
                    List<MachineDetailResponse.DataBean.MachineFaultsBean> machineFaults = data.getMachineFaults();
                    List<MachineDetailResponse.DataBean.MachineHistoryProfitLossBean> machineHistoryProfitLoss = data.getMachineHistoryProfitLoss();
                    faultRecord.setData(machineFaults,machineId,task,true);
                }
        );

    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        machineId = bundle.getInt("machineId");
        String title = getString(R.string.wtjl);
        String[] titles = {title};
        faultRecord = new FaultRecord();
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(faultRecord);
        MyPageAdapter myPageAdapter = new MyPageAdapter(getChildFragmentManager(), fragments, titles);
        binding.vp.setAdapter(myPageAdapter);
        binding.tab.setViewPager(binding.vp);
    }

    @Override
    public void initlisten() {
        binding.uploadFault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadFault uploadFault = new UploadFault();
                Bundle bundle = new Bundle();
                bundle.putInt("machineId", machineId);
                uploadFault.setArguments(bundle);
                String title = getString(R.string.uploadFault);
                task.step2Task("uploadFault", uploadFault, " > " + title);
            }
        });
    }




}
