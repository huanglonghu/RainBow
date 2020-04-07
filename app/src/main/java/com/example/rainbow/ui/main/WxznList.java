package com.example.rainbow.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.MachineGuideBody;
import com.example.rainbow.bean.NoticeListResponse;
import com.example.rainbow.databinding.FragmentWxznListBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.NoticeListAdapter;
import com.example.rainbow.ui.fragment.WxznDetail;
import com.example.rainbow.util.GsonUtil;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class WxznList extends BaseFragment {

    private FragmentWxznListBinding binding;
    private ArrayList<NoticeListResponse.DataBean.ItemsBean> datas;
    private NoticeListAdapter noticeListAdapter;
    private Wxzn wxzn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        wxzn = (Wxzn) getParentFragment();
        wxzn.hideBack();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wxzn_list, container, false);
        initView();
        initData();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        MachineGuideBody machineGuideBody = new MachineGuideBody();
        machineGuideBody.setLimit(20);
        machineGuideBody.setPage(1);
        getWxznList(machineGuideBody);
    }

    private void getWxznList(MachineGuideBody machineGuideBody) {
        HttpUtil.getInstance().getWxznList(machineGuideBody).subscribe(
                str -> {
                    if (machineGuideBody.getPage() == 1 && datas.size() > 0) {
                        datas.clear();
                        noticeListAdapter.clearView();
                    }
                    NoticeListResponse noticeListResponse = GsonUtil.fromJson(str, NoticeListResponse.class);
                    NoticeListResponse.DataBean data = noticeListResponse.getData();
                    if (data != null) {
                        List<NoticeListResponse.DataBean.ItemsBean> items = data.getItems();
                        datas.addAll(items);
                        noticeListAdapter.notifyDataSetChanged();
                    }
                }
        );
    }


    @Override
    public void initView() {
        datas = new ArrayList<>();
        noticeListAdapter = new NoticeListAdapter(getContext(), datas, R.layout.lv_item_notice);
        binding.lvWxzn.setAdapter(noticeListAdapter);
    }

    public void back() {
    }


    @Override
    public void initlisten() {

        binding.lvWxzn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WxznDetail wxznDetail = new WxznDetail();
                NoticeListResponse.DataBean.ItemsBean bean = datas.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("id", bean.getId());
                wxznDetail.setArguments(bundle);
                wxzn.step2wxzn("wxznDetail", wxznDetail);
            }
        });

        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String machineName = binding.machineName.getText().toString();
                String machineModle = binding.machineModle.getText().toString();
                MachineGuideBody machineGuideBody = new MachineGuideBody();
                machineGuideBody.setPage(1);
                machineGuideBody.setLimit(20);
                machineGuideBody.setMachineName(machineName);
                machineGuideBody.setMachineModel(machineModle);
                getWxznList(machineGuideBody);
            }
        });

    }
}
