package com.example.rainbow.ui.fragment.task;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.ShopSettleBody;
import com.example.rainbow.bean.ShopWinlossResponse;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentShopSettleBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ShopSettle extends BaseFragment {


    private FragmentShopSettleBinding binding;
    private int id;
    private int shopId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_settle, container, false);
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
        HttpUtil.getInstance().getShopWinloss(id, shopId).subscribe(
                str -> {
                    ShopWinlossResponse shopWinlossResponse = GsonUtil.fromJson(str, ShopWinlossResponse.class);
                    ShopWinlossResponse.DataBean data = shopWinlossResponse.getData();
                    binding.setData(data);
                }
        );


    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {

        ShopSettleBody shopSettleBody = new ShopSettleBody();
        shopSettleBody.setJobId(id);
        shopSettleBody.setShopId(shopId);

        /**
         * jobId : 0
         * shopId : 0
         * shopWashScore : 0
         * shopDeductionMoney : 0
         * shopPayMoney : 0
         * sign : string
         */
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitMap = binding.signArea.getBitMap();
                if (bitMap != null) {
                    try {
                        File file = binding.signArea.save();
                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                        HttpUtil.getInstance().uploadPicture(filePart).subscribe(
                                str -> {
                                    UploadPictureResponse uploadPictureResponse = GsonUtil.fromJson(str, UploadPictureResponse.class);
                                    String path = uploadPictureResponse.getData();
                                    shopSettleBody.setSign(path);
                                    HttpUtil.getInstance().shopSettle(shopSettleBody).subscribe(
                                            str2 -> {

                                            }
                                    );
                                }
                        );
                    } catch (Exception e) {
                        LogUtil.log("========Exception=======" + e.toString());
                    }

                } else {
                    String toastStr = getString(R.string.toastStr14);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                }


            }
        });


        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.signArea.clear();
            }
        });


    }
}
