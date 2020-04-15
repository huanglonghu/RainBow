package com.example.rainbow.ui.fragment.task;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.ShopSettleBody;
import com.example.rainbow.bean.ShopSettledResponse;
import com.example.rainbow.bean.ShopWinlossResponse;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentShopSettleBinding;
import com.example.rainbow.databinding.FragmentShopSettledBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.InputFilterMax;
import com.example.rainbow.util.InputFilterMax2;
import com.example.rainbow.util.LogUtil;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ShopSettled extends BaseFragment {


    private FragmentShopSettledBinding binding;
    private int id;
    private int shopId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_settled, container, false);
        binding.setPresenter(Presenter.getInstance());
        initData();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        shopId = bundle.getInt("shopId");
        boolean isSettled = bundle.getBoolean("isSettled");
        binding.setIsSettled(isSettled);
        HttpUtil.getInstance().getShopSettleDetail(id, shopId).subscribe(
                str -> {
                    ShopSettledResponse shopSettledResponse = GsonUtil.fromJson(str, ShopSettledResponse.class);
                    ShopSettledResponse.DataBean data = shopSettledResponse.getData();
                    binding.setData(data);
                }
        );

    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {


    }
}
