package com.example.rainbow.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.NoticeListResponse;
import com.example.rainbow.databinding.FragmentNoticeListBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.NoticeListAdapter;
import com.example.rainbow.ui.fragment.NoticeDetail;
import com.example.rainbow.ui.main.Notice;
import com.example.rainbow.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class NoticeList extends BaseFragment {


    private FragmentNoticeListBinding binding;
    private ArrayList<NoticeListResponse.DataBean.ItemsBean> datas;
    private NoticeListAdapter noticeListAdapter;
    private Notice notice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        notice = (Notice) getParentFragment();
        notice.hideBack();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notice_list, container, false);
        initView();
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {

        HttpUtil.getInstance().getNoticeList(1).subscribe(
                str -> {
                    NoticeListResponse noticeListResponse = GsonUtil.fromJson(str, NoticeListResponse.class);
                    NoticeListResponse.DataBean data = noticeListResponse.getData();
                    List<NoticeListResponse.DataBean.ItemsBean> items = data.getItems();
                    datas.clear();
                    datas.addAll(items);
                    noticeListAdapter.notifyDataSetChanged();
                }
        );

    }

    @Override
    public void initView() {
        datas = new ArrayList<>();
        noticeListAdapter = new NoticeListAdapter(getContext(), datas, R.layout.lv_item_notice);
        binding.lvNotice.setAdapter(noticeListAdapter);
    }

    @Override
    public void initlisten() {

        binding.lvNotice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoticeDetail noticeDetail = new NoticeDetail();
                notice.step2NoticeDetail("noticeDetail", noticeDetail);
            }
        });


    }
}
