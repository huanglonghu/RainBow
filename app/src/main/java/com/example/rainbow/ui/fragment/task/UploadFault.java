package com.example.rainbow.ui.fragment.task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentUploadFaultBinding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UploadFault extends BaseFragment {

    private FragmentUploadFaultBinding binding;
    private int machineId;
    private ArrayList<String> pathList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_fault, container, false);
        initData();
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        pathList = new ArrayList<>();
        Bundle bundle = getArguments();
        machineId = bundle.getInt("machineId");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {

        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String faultDescribe = binding.faultDescribe.getText().toString();
                if (pathList.isEmpty()) {
                    Toast.makeText(getContext(), "请拍照上传故障", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb = new StringBuffer();
                int size = pathList.size();
                for (int i = 0; i < size; i++) {
                    String path = pathList.get(i);
                    if (i != size - 1) {
                        sb.append(path + ",");
                    } else {
                        sb.append(path);
                    }
                }
                if (TextUtils.isEmpty(faultDescribe)) {
                    Toast.makeText(getContext(), "请输入问题描述", Toast.LENGTH_SHORT).show();
                    return;
                }
                HttpUtil.getInstance().uploadFault(machineId, sb.toString(), faultDescribe).subscribe(
                        str -> {
                            Toast.makeText(getContext(), "故障提交成功", Toast.LENGTH_SHORT).show();
                        }
                );

            }
        });


        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pathList.size() == 4) {
                    Toast.makeText(getContext(), "最多上传四张照片", Toast.LENGTH_SHORT).show();
                    return;
                }
                goCamera();
            }
        });


    }


    //激活相机操作
    private void goCamera() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //第二个参数为 包名.fileprovider
            uri = FileProvider.getUriForFile(getActivity(), "com.example.rainbow.fileprovider", file);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        new ActivityResultHandler.Builder().requestCode(ActivityResultHandler.REQUEST_TAKE_PHOTO).hadlerStrategy(new HandlerStrategy() {
            @Override
            public void onActivityResult() {
                String photoPath;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoPath = String.valueOf(file);
                } else {
                    photoPath = uri.getEncodedPath();
                }
                Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                HttpUtil.getInstance().uploadPicture(filePart).subscribe(
                        str -> {
                            UploadPictureResponse uploadPictureResponse = GsonUtil.fromJson(str, UploadPictureResponse.class);
                            String path = uploadPictureResponse.getData();
                            pathList.add(path);
                            switch (pathList.size()) {
                                case 1:
                                    binding.p1.setVisibility(View.VISIBLE);
                                    binding.p1.setImageBitmap(bitmap);
                                    break;
                                case 2:
                                    binding.p2.setVisibility(View.VISIBLE);
                                    binding.p2.setImageBitmap(bitmap);
                                    break;
                                case 3:
                                    binding.p3.setVisibility(View.VISIBLE);
                                    binding.p3.setImageBitmap(bitmap);
                                    break;
                                case 4:
                                    binding.p4.setVisibility(View.VISIBLE);
                                    binding.p4.setImageBitmap(bitmap);
                                    break;
                            }
                            binding.p1.setImageBitmap(bitmap);
                        }
                );

            }
        }).intent(intent).activity((AppCompatActivity) getActivity()).build().startActivityForResult();

    }





}
