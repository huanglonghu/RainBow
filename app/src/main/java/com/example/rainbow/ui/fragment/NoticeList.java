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
import com.example.rainbow.ui.main.Notice;
import com.example.rainbow.util.GsonUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

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
        initlisten();
        return binding.getRoot();
    }


    private int page = 1;

    @Override
    public void initData() {

        getNoticeList();
    }

    private void getNoticeList() {
        HttpUtil.getInstance().getNoticeList(page).subscribe(
                str -> {
                    binding.refreshLayout.finishLoadMore();
                    NoticeListResponse noticeListResponse = GsonUtil.fromJson(str, NoticeListResponse.class);
                    NoticeListResponse.DataBean data = noticeListResponse.getData();
                    List<NoticeListResponse.DataBean.ItemsBean> items = data.getItems();
                    if (items != null && items.size() > 0) {
                        datas.addAll(items);
                        noticeListAdapter.notifyDataSetChanged();
                        page++;
                    } else {
                        binding.refreshLayout.setEnableLoadMore(false);
                    }

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

        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        binding.lvNotice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NoticeDetail noticeDetail = new NoticeDetail();
                NoticeListResponse.DataBean.ItemsBean bean = datas.get(i);
                int id = bean.getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                noticeDetail.setArguments(bundle);
                notice.step2NoticeDetail("noticeDetail", noticeDetail);
            }
        });

        binding.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getNoticeList();
            }
        });

    }
}
