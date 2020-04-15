package com.example.rainbow.ui.fragment.task;

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
import com.example.rainbow.bean.RouteSettleBody;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentSettleAccountsBinding;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.customView.LinePathView;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import java.io.File;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LineSettle extends BaseFragment {

    private FragmentSettleAccountsBinding binding;
    private RouteSettleBody routeSettleBody;
    private Task task;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        task = (Task) getParentFragment();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_settle_accounts, container, false);
        routeSettleBody = new RouteSettleBody();
        initData();
        initlisten();
        return binding.getRoot();
    }

    private boolean isSettle;

    @Override
    public void initData() {
        int id = getArguments().getInt("id");
        routeSettleBody.setJobId(id);
        HttpUtil.getInstance().getRouteWinlostDetail(id).subscribe(
                str -> {
                    isSettle = true;
                    LineSettleResponse lineSettleResponse = GsonUtil.fromJson(str, LineSettleResponse.class);
                    LineSettleResponse.DataBean data = lineSettleResponse.getData();
                    binding.setData(data);
                }
        );
    }

    @Override
    public void initView() {

    }


    @Override
    public void initlisten() {
        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.signArea.clear();
            }
        });

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


        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSettle) {
                    String zsf = binding.zsf.getText().toString();
                    if (TextUtils.isEmpty(zsf)) {
                        String toastStr = getString(R.string.toastStr26);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v = Double.parseDouble(zsf);
                    routeSettleBody.setHotelMoney(v);
                    String zwf = binding.zwf.getText().toString();
                    if (TextUtils.isEmpty(zwf)) {
                        String toastStr = getString(R.string.toastStr27);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v2 = Double.parseDouble(zwf);
                    routeSettleBody.setOtherMoney(v2);

                    String ryf = binding.ryf.getText().toString();
                    if (TextUtils.isEmpty(ryf)) {
                        String toastStr = getString(R.string.toastStr28);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v3 = Double.parseDouble(ryf);
                    routeSettleBody.setFuelMoney(v3);

                    String cyf = binding.cyf.getText().toString();
                    if (TextUtils.isEmpty(cyf)) {
                        String toastStr = getString(R.string.toastStr29);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double v4 = Double.parseDouble(cyf);
                    routeSettleBody.setEatMoney(v4);
                    String remark = binding.remark.getText().toString();
                    routeSettleBody.setRemarks(remark);
                    String note = binding.note.getText().toString();
                    if (TextUtils.isEmpty(note)) {
                        String toastStr = getString(R.string.toastStr30);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    routeSettleBody.setMoneyNumber(Integer.parseInt(note));

                    String coin = binding.coin.getText().toString();
                    if (TextUtils.isEmpty(coin)) {
                        String toastStr = getString(R.string.toastStr31);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    routeSettleBody.setCoinNumber(Integer.parseInt(coin));

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
                                        file.delete();
                                        UploadPictureResponse uploadPictureResponse = GsonUtil.fromJson(str, UploadPictureResponse.class);
                                        String data = uploadPictureResponse.getData();
                                        routeSettleBody.setSign(data);
                                        HttpUtil.getInstance().routeSettle(routeSettleBody).subscribe(
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
            }
        });

    }
}
