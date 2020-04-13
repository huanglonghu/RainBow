package com.example.rainbow.ui.fragment.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.ShopFaultResponse;
import com.example.rainbow.bean.ShopProfitLossResponse;
import com.example.rainbow.catche.Loader.RxImageLoader;
import com.example.rainbow.databinding.FragmentShop2Binding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.ui.adapter.ShopFaultListAdapter;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.ImagUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

public class Shop2 extends BaseFragment {

    private FragmentShop2Binding binding;
    private int id;
    private int shopId;
    private Task task;
    private ShopFaultResponse.DataBean data;
    private List<ShopFaultResponse.DataBean.MachineFaultBean> datas;
    private ShopFaultListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop2, container, false);
            binding.setPresenter(Presenter.getInstance());
            initView();
            initlisten();
        }
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        if (datas.size() > 0) {
            datas.clear();
            adapter.clearView();
        }
        HttpUtil.getInstance().getShopfaultDetail(id, shopId).subscribe(
                str -> {
                    handleData(str);
                }
        );

    }

    private void handleData(String str) {
        ShopFaultResponse shopDetailResponse = GsonUtil.fromJson(str, ShopFaultResponse.class);
        data = shopDetailResponse.getData();
        binding.setData(data);
        String image = data.getImage();
        String url = ImagUtil.handleUrl(image);
        if (!TextUtils.isEmpty(url)) {
            RxImageLoader.with(getContext()).load(url).into(binding.iv, 1);
        }
        List<ShopFaultResponse.DataBean.MachineFaultBean> machineFault = data.getMachineFault();
        if (machineFault != null && machineFault.size() > 0) {
            datas.addAll(machineFault);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        shopId = bundle.getInt("businessId");
        datas = new ArrayList<>();
        adapter = new ShopFaultListAdapter(getContext(), datas, R.layout.lv_item_shop_fault);
        binding.lvShop2.setAdapter(adapter);
    }

    @Override
    public void initlisten() {

        binding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String coordinate = data.getCoordinate();
                if (!TextUtils.isEmpty(coordinate)) {
                    String[] split = coordinate.split(",");
                    double la = Double.parseDouble(split[0]);
                    double lon = Double.parseDouble(split[1]);
                    Presenter.getInstance().startNaviGoogle(la, lon);
                }
            }
        });

        binding.toSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signed signed = new Signed();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putString("shopName", data.getShopName());
                bundle.putInt("shopId", shopId);
                signed.setArguments(bundle);
                String title = getString(R.string.sign);
                task.step2Task("sign", signed, " > " + title);
            }
        });

        binding.lvShop2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                ShopFaultResponse.DataBean.MachineFaultBean bean = datas.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("machineName", bean.getMachineName());
                bundle.putInt("machineId", bean.getId());
                bundle.putInt("id", shopId);
                bundle.putBoolean("isRepair", true);
                bundle.putBoolean("isShopSign", data.isIsSignIn());
                Machine2 machine = new Machine2();
                machine.setArguments(bundle);
                task.step2Task("machine2", machine, " > " + bean.getMachineName());
            }
        });


    }


    public void toggle(String name, BaseFragment fragment, String title) {
        task.step2Task(name, fragment, title);
    }



}
