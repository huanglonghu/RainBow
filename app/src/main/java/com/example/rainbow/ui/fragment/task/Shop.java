package com.example.rainbow.ui.fragment.task;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.ShopDetailResponse;
import com.example.rainbow.databinding.FragmentShopBinding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.ui.adapter.MyPageAdapter;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;

public class Shop extends BaseFragment {

    private FragmentShopBinding binding;
    private int id;
    private int shopId;
    private ArrayList<BaseFragment> fragments;
    private Task task;
    private ShopDetailResponse.DataBean data;
    private boolean isRepair;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false);
            binding.setPresenter(Presenter.getInstance());
            initView();
            initlisten();
        }
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        HttpUtil.getInstance().getShopDetail(id, shopId).subscribe(
                str -> {
                    ShopDetailResponse shopDetailResponse = GsonUtil.fromJson(str, ShopDetailResponse.class);
                    data = shopDetailResponse.getData();
                    List<ShopDetailResponse.DataBean.MachineProfitLossBean> machineProfitLoss = data.getMachineProfitLoss();
                    for (int i = 0; i < fragments.size(); i++) {
                        ShopItem shopItem = (ShopItem) fragments.get(i);
                        shopItem.setData(machineProfitLoss, id);
                    }
                    binding.setData(data);
                }
        );
    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        shopId = bundle.getInt("businessId");
        isRepair = bundle.getBoolean("isRepair");
        binding.setIsRepair(isRepair);
        fragments = new ArrayList<>();
        String[] titles;
        if (isRepair) {
            titles = new String[1];
            titles[0] = "待处理问题";
            ShopItem shopItem = new ShopItem();
            Bundle b = new Bundle();
            b.putInt("type", 2);
            b.putBoolean("isRepair", isRepair);
            shopItem.setArguments(b);
            fragments.add(shopItem);
        } else {
            titles = getResources().getStringArray(R.array.shopItemTitle);
            for (int i = 0; i < 2; i++) {
                ShopItem shopItem = new ShopItem();
                Bundle b = new Bundle();
                b.putInt("type", i + 1);
                b.putBoolean("isRepair", isRepair);
                shopItem.setArguments(b);
                fragments.add(shopItem);
            }
        }
        MyPageAdapter myPageAdapter = new MyPageAdapter(getChildFragmentManager(), fragments, titles);
        binding.vp.setAdapter(myPageAdapter);
        binding.tab.setViewPager(binding.vp);
    }

    @Override
    public void initlisten() {

        binding.line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Presenter.getInstance().startNaviGoogle(23.1066805, 113.3245904);
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

        binding.shopSettle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ShopSettle shopSettle = new ShopSettle();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                bundle.putInt("shopId", shopId);
                shopSettle.setArguments(bundle);
                String title = getString(R.string.shopSettle);
                task.step2Task("shopSettle", shopSettle, " > " + title);
            }
        });

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goCamera();
            }
        });

    }


    public void toggle(String name, BaseFragment fragment, String title) {
        task.step2Task(name, fragment, title);
    }


    //激活相机操作
    private void goCamera() {
        File cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
            uri = FileProvider.getUriForFile(getActivity(), "com.example.rainbow.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        new ActivityResultHandler.Builder().requestCode(ActivityResultHandler.REQUEST_TAKE_PHOTO).hadlerStrategy(new HandlerStrategy() {
            @Override
            public void onActivityResult() {

            }
        }).intent(intent).activity((AppCompatActivity) getActivity()).build().startActivityForResult();

    }


}
