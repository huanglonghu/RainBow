package com.example.rainbow.ui.fragment.task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.MachineDetailResponse;
import com.example.rainbow.bean.MachineSettleBody;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentMachineBinding;
import com.example.rainbow.databinding.LayoutUploadPictureBinding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.ui.adapter.MyPageAdapter;
import com.example.rainbow.ui.adapter.UploadPageAdapter;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import okhttp3.MultipartBody;

public class Machine extends BaseFragment {

    private FragmentMachineBinding binding;
    private int id;
    private int machineId;
    private MachineDetailResponse.DataBean data;
    private Task task;
    private HistoryRecord historyRecord;
    private FaultRecord faultRecord;
    private MachineSettleBody machineSettleBody;
    private boolean isShopSign;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_machine, container, false);
            binding.setPresenter(Presenter.getInstance());
            initView();
            initlisten();
            initData();
        }

        return binding.getRoot();
    }

    @Override
    public void initData() {

        HttpUtil.getInstance().getMachineDetail(id, machineId).subscribe(
                str -> {
                    MachineDetailResponse machineDetailResponse = GsonUtil.fromJson(str, MachineDetailResponse.class);
                    data = machineDetailResponse.getData();
                    List<MachineDetailResponse.DataBean.MachineFaultsBean> machineFaults = data.getMachineFaults();
                    List<MachineDetailResponse.DataBean.MachineHistoryProfitLossBean> machineHistoryProfitLoss = data.getMachineHistoryProfitLoss();
                    binding.setData(data);
                    if (machineHistoryProfitLoss != null && machineHistoryProfitLoss.size() > 0) {
                        if (data.isIsSettled()) {
                            if (machineHistoryProfitLoss.size() > 1) {
                                MachineDetailResponse.DataBean.MachineHistoryProfitLossBean bean2 = machineHistoryProfitLoss.get(1);
                                binding.sctb.setText(bean2.getTotalBet() + "");
                                binding.scxf.setText(bean2.getTotalWashScore() + "");
                                binding.sccb.setText(bean2.getTotalOut() + "");
                            }
                            binding.etCb.setText(data.getTotalOut() + "");
                            binding.etTb.setText(data.getTotalBet() + "");
                            binding.etXf.setText(data.getTotalWashScore() + "");
                        } else {
                            if (machineHistoryProfitLoss.size() > 0) {
                                MachineDetailResponse.DataBean.MachineHistoryProfitLossBean bean2 = machineHistoryProfitLoss.get(0);
                                binding.sctb.setText(bean2.getTotalBet() + "");
                                binding.scxf.setText(bean2.getTotalWashScore() + "");
                                binding.sccb.setText(bean2.getTotalOut() + "");
                            }

                        }
                        historyRecord.setData(machineHistoryProfitLoss);
                        faultRecord.setData(machineFaults, id, task, false, isShopSign);
                    }
                }
        );

    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        machineId = bundle.getInt("machineId");
        isShopSign = bundle.getBoolean("isShopSign");
        String[] titles = getResources().getStringArray(R.array.machinItemTitle);
        historyRecord = new HistoryRecord();
        faultRecord = new FaultRecord();
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(historyRecord);
        fragments.add(faultRecord);
        MyPageAdapter myPageAdapter = new MyPageAdapter(getChildFragmentManager(), fragments, titles);
        binding.vp.setAdapter(myPageAdapter);
        binding.tab.setViewPager(binding.vp);
        ArrayList<View> views = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            LayoutUploadPictureBinding uploadPictureBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_upload_picture, null, false);
            views.add(uploadPictureBinding.getRoot());
            int finalI = i;
            uploadPictureBinding.upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectPhoto(uploadPictureBinding.picture, finalI);
                }
            });
        }
        UploadPageAdapter uploadPageAdapter = new UploadPageAdapter(views);
        binding.vpUpload.setAdapter(uploadPageAdapter);

    }

    @Override
    public void initlisten() {

        binding.uploadFault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShopSign) {
                    UploadFault uploadFault = new UploadFault();
                    Bundle bundle = new Bundle();
                    bundle.putInt("machineId", machineId);
                    uploadFault.setArguments(bundle);
                    String title = getString(R.string.uploadFault);
                    task.step2Task("uploadFault", uploadFault, " > " + title);
                } else {
                    String toastStr = getString(R.string.toastStr25);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                }

            }
        });
        machineSettleBody = new MachineSettleBody();
        machineSettleBody.setJobId(id);
        machineSettleBody.setMachineId(machineId);
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isShopSign) {

                    String tbStr = binding.etTb.getText().toString();
                    String xfStr = binding.etXf.getText().toString();
                    String cbStr = binding.etCb.getText().toString();
                    if (TextUtils.isEmpty(tbStr)) {
                        String toastStr = getString(R.string.toastStr32);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(xfStr)) {
                        String toastStr = getString(R.string.toastStr33);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (TextUtils.isEmpty(cbStr)) {
                        String toastStr = getString(R.string.toastStr34);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(machineSettleBody.getNumberImage())) {
                        String toastStr = getString(R.string.toastStr35);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (TextUtils.isEmpty(machineSettleBody.getCodeImage())) {
                        String toastStr = getString(R.string.toastStr36);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    int totalTb = Integer.parseInt(tbStr);
                    int totalXf = Integer.parseInt(xfStr);
                    int totalCb = Integer.parseInt(cbStr);
                    machineSettleBody.setTotalBet(totalTb);
                    machineSettleBody.setTotalOut(totalCb);
                    machineSettleBody.setTotalWashScore(totalXf);
                    String toastStr2 = getString(R.string.toastStr5);
                    HttpUtil.getInstance().machineSettle(machineSettleBody).subscribe(
                            str -> {
                                Toast.makeText(getContext(), toastStr2, Toast.LENGTH_SHORT).show();
                                MachineDetailResponse machineDetailResponse = GsonUtil.fromJson(str, MachineDetailResponse.class);
                                MachineDetailResponse.DataBean dataBean = machineDetailResponse.getData();
                                data.setIsSettled(true);
                                data.setBet(dataBean.getBet());
                                data.setOut(dataBean.getOut());
                                data.setWashScore(dataBean.getWashScore());
                                data.setProfitLoss(dataBean.getProfitLoss());
                                binding.setData(data);
                                initData();
                            }
                    );

                } else {
                    String toastStr = getString(R.string.toastStr25);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                }

            }
        });
        addTextChangeListener(binding.etTb,binding.sctb,binding.bctb);
        addTextChangeListener(binding.etXf,binding.scxf,binding.bcxf);
        addTextChangeListener(binding.etCb,binding.sccb,binding.bccb);
    }

    private void addTextChangeListener(EditText et1, TextView tv1, TextView tv2) {
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int v = 0;
                if (!TextUtils.isEmpty(s)) {
                    v = Integer.parseInt(s.toString());

                }
                String str = tv1.getText().toString();
                int v2 = 0;
                if (!TextUtils.isEmpty(str)) {
                    v2 = Integer.parseInt(str);
                }
                if (v >= v2) {
                    tv2.setText(String.valueOf(v - v2) + "");
                }
                LogUtil.log("========SSSSSS=========" + s);
            }
        });
    }


    public void selectPhoto(View view, int type) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        new ActivityResultHandler.Builder().hadlerStrategy(new HandlerStrategy() {
            @Override
            public void onActivityResult(MultipartBody.Part filePart, Bitmap bitmap) {
                HttpUtil.getInstance().uploadPicture(filePart).subscribe(
                        str -> {
                            BitmapDrawable d = new BitmapDrawable(bitmap);
                            view.setBackground(d);
                            UploadPictureResponse uploadPictureResponse = GsonUtil.fromJson(str, UploadPictureResponse.class);
                            String path = uploadPictureResponse.getData();
                            switch (type) {
                                case 0:
                                    machineSettleBody.setNumberImage(path);
                                    break;
                                case 1:
                                    machineSettleBody.setCodeImage(path);
                                    break;
                            }
                        }
                );

            }
        }).requestCode(ActivityResultHandler.REQUEST_SELECT_PHOTO_CROP).intent(intent).activity((AppCompatActivity) getActivity()).build().startActivityForResult();
    }


}
