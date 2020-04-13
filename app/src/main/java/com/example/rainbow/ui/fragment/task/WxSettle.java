package com.example.rainbow.ui.fragment.task;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.LineSettleResponse;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.bean.WxRouteSettleBody;
import com.example.rainbow.databinding.FragmentSettleWxBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.customView.LinePathView;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.ui.widget.NetLoading;
import com.example.rainbow.util.GsonUtil;

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WxSettle extends BaseFragment {

    private FragmentSettleWxBinding binding;
    private NetLoading netLoading;
    private int id;
    private Task task;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        task = (Task) getParentFragment();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settle_wx, container, false);
        initData();
        initlisten();
        return binding.getRoot();
    }

    private boolean isSettle;

    @Override
    public void initData() {

        id = getArguments().getInt("id");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {

        binding.signArea.setTouchListener(new LinePathView.TouchListener() {
            @Override
            public void onTouch(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //同意ScrollView截断点击事件，ScrollView可滑动
                    binding.scrollView.requestDisallowInterceptTouchEvent(false);
                } else {
                    //不同意ScrollView截断点击事件，点击事件由子View处理
                    binding.scrollView.requestDisallowInterceptTouchEvent(true);
                }
            }
        });

        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.signArea.clear();
            }
        });

        WxRouteSettleBody wxRouteSettleBody = new WxRouteSettleBody();
        wxRouteSettleBody.setJobId(id);
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    String zsf = binding.zsf.getText().toString();
                    if (TextUtils.isEmpty(zsf)) {
                        String toastStr = getString(R.string.toastStr26);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v = Double.parseDouble(zsf);
                    wxRouteSettleBody.setHotelMoney(v);
                    String zwf = binding.zwf.getText().toString();
                    if (TextUtils.isEmpty(zwf)) {
                        String toastStr = getString(R.string.toastStr27);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v2 = Double.parseDouble(zwf);
                    wxRouteSettleBody.setOtherMoney(v2);

                    String ryf = binding.ryf.getText().toString();
                    if (TextUtils.isEmpty(ryf)) {
                        String toastStr = getString(R.string.toastStr28);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v3 = Double.parseDouble(ryf);
                    wxRouteSettleBody.setFuelMoney(v3);

                    String cyf = binding.cyf.getText().toString();
                    if (TextUtils.isEmpty(cyf)) {
                        String toastStr = getString(R.string.toastStr29);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v4 = Double.parseDouble(cyf);
                    wxRouteSettleBody.setEatMoney(v4);
                    String remark = binding.remark.getText().toString();
                    if (TextUtils.isEmpty(remark)) {
                        String toastStr = getString(R.string.toastStr11);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    wxRouteSettleBody.setRemarks(remark);

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
                                        String data = uploadPictureResponse.getData();
                                        wxRouteSettleBody.setSign(data);
                                        HttpUtil.getInstance().wxRouteSettle(wxRouteSettleBody).subscribe(
                                                str2 -> {
                                                    String toastStr = getString(R.string.toastSrt37);
                                                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                                                    task.back();
                                                }
                                        );
                                    }
                            );

                        } catch (Exception e) {
                        }
                    } else {
                        String toastStr = getString(R.string.toastStr14);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    }


            }
        });

    }
}
