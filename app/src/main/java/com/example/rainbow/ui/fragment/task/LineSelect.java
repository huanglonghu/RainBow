package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.GetJobDetailResponse;
import com.example.rainbow.databinding.FragmentSelectLineBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.adapter.SelectLineAdapter;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;
import com.jakewharton.rxbinding2.widget.RxAdapterView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class LineSelect extends BaseFragment {

    private FragmentSelectLineBinding binding;
    private int id;
    private List<GetJobDetailResponse.DataBean.ShopsBean> datas;
    private SelectLineAdapter adapter;
    private Task task;
    private boolean isRepair;
    private boolean[] isSignArray;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_line, container, false);
            initView();
            initlisten();
        }
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        datas.clear();
        adapter.clearView();
        HttpUtil.getInstance().getJobDetail(id).subscribe(
                str -> {
                    GetJobDetailResponse gjdr = GsonUtil.fromJson(str, GetJobDetailResponse.class);
                    GetJobDetailResponse.DataBean data = gjdr.getData();
                    if (data != null) {
                        List<GetJobDetailResponse.DataBean.ShopsBean> shops = data.getShops();
                        if (shops != null && shops.size() > 0) {
                            isSignArray = new boolean[shops.size()];
                            for (int i = 0; i < shops.size(); i++) {
                                GetJobDetailResponse.DataBean.ShopsBean bean = shops.get(i);
                                boolean isSignIn = bean.isIsSignIn();
                                isSignArray[i] = isSignIn;
                            }
                            datas.addAll(data.getShops());
                            adapter.notifyDataSetChanged();
                        }
                        binding.setData(data);
                    }
                }
        );

    }

    @Override
    public void initView() {

        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        isRepair = bundle.getBoolean("isRepair");
        datas = new ArrayList<>();
        adapter = new SelectLineAdapter(getContext(), datas, R.layout.lv_item_line);
        binding.lvLine.setAdapter(adapter);
    }

    @Override
    public void initlisten() {

        RxAdapterView.itemClicks(binding.lvLine).throttleFirst(1, TimeUnit.SECONDS).subscribe(
                position -> {
                    GetJobDetailResponse.DataBean.ShopsBean bean = datas.get(position);
                    if (position == 0) {
                        toShop(bean);
                    } else {
                        boolean isCanGo=true;
                        for (int i = 0; i < position; i++) {
                            GetJobDetailResponse.DataBean.ShopsBean bean2 = datas.get(i);
                            boolean b = bean2.isIsSignIn();
                            boolean c = bean2.isIsNotGo();
                            boolean a = b|| c;
                            if (!a) {
                                isCanGo = false;
                            }
                        }

                        if (isCanGo) {
                            toShop(bean);
                        } else {
                            String content = getString(R.string.toastStr23);
                            Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        binding.totalSettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isRepair) {
                    WxSettle wxSettle = new WxSettle();
                    String title = getString(R.string.settle);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    wxSettle.setArguments(bundle);
                    task.step2Task("wxSettle", wxSettle, " > " + title);
                } else {
                    LineSettle lineSettle = new LineSettle();
                    String title = getString(R.string.settle);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", id);
                    lineSettle.setArguments(bundle);
                    task.step2Task("lineSettle", lineSettle, " > " + title);
                }

            }
        });


    }

    private void toShop(GetJobDetailResponse.DataBean.ShopsBean bean) {
        if (isRepair) {
            Shop2 shop = new Shop2();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            bundle.putInt("businessId", bean.getId());
            bundle.putString("shopName", bean.getShopName());
            shop.setArguments(bundle);
            task.step2Task("shop2", shop, " > " + bean.getShopName());
        } else {
            Shop shop = new Shop();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            bundle.putInt("businessId", bean.getId());
            bundle.putString("shopName", bean.getShopName());
            shop.setArguments(bundle);
            task.step2Task("shop", shop, " > " + bean.getShopName());
        }

    }
}
