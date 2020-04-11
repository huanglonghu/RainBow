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
import com.example.rainbow.bean.ShopWinlossResponse;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentShopSettleBinding;
import com.example.rainbow.net.HttpUtil;
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

public class ShopSettle extends BaseFragment {


    private FragmentShopSettleBinding binding;
    private int id;
    private int shopId;
    private boolean isShopSign;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop_settle, container, false);
        binding.setPresenter(Presenter.getInstance());
        initData();
        initlisten();
        return binding.getRoot();
    }

    private boolean isSettle;

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        shopId = bundle.getInt("shopId");
        isShopSign = bundle.getBoolean("isShopSign");
        HttpUtil.getInstance().getShopWinloss(id, shopId).subscribe(
                str -> {
                    isSettle = true;
                    ShopWinlossResponse shopWinlossResponse = GsonUtil.fromJson(str, ShopWinlossResponse.class);
                    ShopWinlossResponse.DataBean data = shopWinlossResponse.getData();
                    binding.setData(data);
                    double arrears = data.getArrears();
                    String toastStr1 = getString(R.string.toastStr40);
                    binding.etDkje.setFilters(new InputFilter[]{new InputFilterMax2(getContext(), toastStr1, arrears)});
                    int actualWashScore = data.getActualWashScore();
                    String toastStr2 = getString(R.string.toastStr41);
                    binding.xf.setFilters(new InputFilter[]{new InputFilterMax(getContext(), toastStr2, actualWashScore)});
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
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShopSign) {
                    if (isSettle) {
                        String xf = binding.xf.getText().toString();
                        if (TextUtils.isEmpty(xf)) {
                            String toastStr = getString(R.string.toastStr24);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        shopSettleBody.setShopWashScore(Integer.parseInt(xf));

                        String dkStr = binding.etDkje.getText().toString();
                        if (TextUtils.isEmpty(dkStr)) {
                            String toastStr = getString(R.string.toastStr38);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        double dk = Double.parseDouble(dkStr);
                        shopSettleBody.setShopDeductionMoney(dk);

                        String sfStr = binding.etSfje.getText().toString();
                        if (TextUtils.isEmpty(sfStr)) {
                            String toastStr = getString(R.string.toastStr39);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        double sf = Double.parseDouble(sfStr);
                        shopSettleBody.setShopPayMoney(sf);
                        if (!binding.signArea.isEmpty()) {
                            try {
                                View root = binding.getRoot();
                                Bitmap bitmap2 = Bitmap.createBitmap(root.getWidth(), root.getHeight(), Bitmap.Config.ARGB_8888);
                                Canvas canvas = new Canvas();
                                canvas.setBitmap(bitmap2);
                                root.draw(canvas);
                                File file = Presenter.getInstance().save(getContext(), bitmap2);
                                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                                HttpUtil.getInstance().uploadPicture(filePart).subscribe(
                                        str -> {
                                            UploadPictureResponse uploadPictureResponse = GsonUtil.fromJson(str, UploadPictureResponse.class);
                                            String path = uploadPictureResponse.getData();
                                            shopSettleBody.setSign(path);
                                            HttpUtil.getInstance().shopSettle(shopSettleBody).subscribe(
                                                    str2 -> {
                                                        Presenter.getInstance().back();
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
                } else {
                    String toastStr = getString(R.string.toastStr25);
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
