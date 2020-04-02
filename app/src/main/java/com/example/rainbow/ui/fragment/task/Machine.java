package com.example.rainbow.ui.fragment.task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            task = (Task) getParentFragment();
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_machine, container, false);
            binding.setPresenter(Presenter.getInstance());
            initView();
            initlisten();
        }
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {

        HttpUtil.getInstance().getMachineDetail(id, machineId).subscribe(
                str -> {
                    MachineDetailResponse machineDetailResponse = GsonUtil.fromJson(str, MachineDetailResponse.class);
                    data = machineDetailResponse.getData();
                    if (data.isIsSettled()) {
                        binding.setData(data);
                        binding.etCb.setText(data.getTotalOut() + "");
                        binding.etTb.setText(data.getTotalBet() + "");
                        binding.etXf.setText(data.getTotalWashScore() + "");
                    }
                    List<MachineDetailResponse.DataBean.MachineFaultsBean> machineFaults = data.getMachineFaults();
                    List<MachineDetailResponse.DataBean.MachineHistoryProfitLossBean> machineHistoryProfitLoss = data.getMachineHistoryProfitLoss();
                    historyRecord.setData(machineHistoryProfitLoss);
                    faultRecord.setData(machineFaults,machineId);
                }
        );

    }

    @Override
    public void initView() {
        Bundle bundle = getArguments();
        id = bundle.getInt("id");
        machineId = bundle.getInt("machineId");
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
                UploadFault uploadFault = new UploadFault();
                Bundle bundle = new Bundle();
                bundle.putInt("machineId", machineId);
                uploadFault.setArguments(bundle);
                String title = getString(R.string.uploadFault);
                task.step2Task("uploadFault", uploadFault, " > " + title);
            }
        });
        machineSettleBody = new MachineSettleBody();
        machineSettleBody.setJobId(id);
        machineSettleBody.setMachineId(machineId);

        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tbStr = binding.etTb.getText().toString();
                String xfStr = binding.etXf.getText().toString();
                String cbStr = binding.etCb.getText().toString();
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
            }
        });


    }


    public void toggle(String name, BaseFragment fragment, String title) {
        task.step2Task(name, fragment, title);
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
