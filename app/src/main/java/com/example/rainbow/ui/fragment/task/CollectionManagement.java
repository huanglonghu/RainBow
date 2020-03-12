package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.GetJobsResponse;
import com.example.rainbow.databinding.FragmentSkglBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.CollectionManagementAdapter;
import com.example.rainbow.ui.fragment.MainFragment;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class CollectionManagement extends BaseFragment {

    private FragmentSkglBinding binding;
    private CollectionManagementAdapter adapter;
    private List<GetJobsResponse.DataBean.ItemsBean> datas;
    private Task task;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_skgl, container, false);
            initView();
            initData();
            initlisten();
        }
        return binding.getRoot();
    }

    @Override
    public void initData() {

        HttpUtil.getInstance().getJob().subscribe(
                str -> {

                    GetJobsResponse gjr = GsonUtil.fromJson(str, GetJobsResponse.class);
                    GetJobsResponse.DataBean data = gjr.getData();
                    datas.addAll(data.getItems());
                    adapter.notifyDataSetChanged();
                }
        );

    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        adapter = new CollectionManagementAdapter(getContext(), datas, R.layout.lv_item_skgl);
        binding.lvCollectionManagement.setAdapter(adapter);
    }

    @Override
    public void initlisten() {

        binding.lvCollectionManagement.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GetJobsResponse.DataBean.ItemsBean bean = datas.get(position);
                LineSelect lineSelect = new LineSelect();
                Bundle bundle = new Bundle();
                bundle.putString("name", bean.getRouteName());
                bundle.putInt("id", bean.getId());
                lineSelect.setArguments(bundle);
                task.step2Task("lineSelect", lineSelect," > "+bean.getRouteName());
            }
        });

    }
}
