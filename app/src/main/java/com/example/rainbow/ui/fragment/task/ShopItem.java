package com.example.rainbow.ui.fragment.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.ShopDetailResponse;
import com.example.rainbow.databinding.FragmentShopitemBinding;
import com.example.rainbow.ui.adapter.ShopItemAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

public class ShopItem extends BaseFragment {

    private FragmentShopitemBinding binding;
    private ArrayList<ShopDetailResponse.DataBean.MachineProfitLossBean> datas;
    private ShopItemAdapter shopItemAdapter;
    private Shop shop;
    private boolean isRepair;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        shop = (Shop) getParentFragment();
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopitem, container, false);
            initView();
            initData();
            initlisten();
        }
        return binding.getRoot();
    }

    @Override
    public void initData() {


    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        int type = bundle.getInt("type");
        isRepair = bundle.getBoolean("isRepair");
        datas = new ArrayList<>();
        shopItemAdapter = new ShopItemAdapter(getContext(), datas, R.layout.lv_item_shop, type);
        binding.lvShopItem.setAdapter(shopItemAdapter);
    }

    private int id;

    public void setData(List<ShopDetailResponse.DataBean.MachineProfitLossBean> machineProfitLoss, int id) {
        this.id = id;
        datas.clear();
        datas.addAll(machineProfitLoss);
        shopItemAdapter.notifyDataSetChanged();
    }


    @Override
    public void initlisten() {

        binding.lvShopItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long i) {
                if (isRepair) {
                    ShopDetailResponse.DataBean.MachineProfitLossBean machineProfitLossBean = datas.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("machineName", machineProfitLossBean.getMachineName());
                    bundle.putInt("machineId", machineProfitLossBean.getId());
                    bundle.putInt("id", id);
                    Machine2 machine = new Machine2();
                    machine.setArguments(bundle);
                    shop.toggle("machine", machine, " > " + machineProfitLossBean.getMachineName());
                } else {
                    ShopDetailResponse.DataBean.MachineProfitLossBean machineProfitLossBean = datas.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("machineName", machineProfitLossBean.getMachineName());
                    bundle.putInt("machineId", machineProfitLossBean.getId());
                    bundle.putInt("id", id);
                    Machine machine = new Machine();
                    machine.setArguments(bundle);
                    shop.toggle("machine", machine, " > " + machineProfitLossBean.getMachineName());
                }


            }
        });


    }
}
