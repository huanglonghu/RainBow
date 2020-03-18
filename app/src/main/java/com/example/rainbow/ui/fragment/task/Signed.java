package com.example.rainbow.ui.fragment.task;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.rainbow.bean.SignBody;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.databinding.FragmentSignedBinding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.util.GsonUtil;
import java.io.File;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class Signed extends BaseFragment {

    private FragmentSignedBinding binding;
    private SignBody signBody;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signed, container, false);
        initlisten();
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initlisten() {
        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        int shopId = bundle.getInt("shopId");
        UserBean userBean = UserOption.getInstance().querryUser();
        signBody = new SignBody();
        signBody.setJobId(id);
        signBody.setShopId(shopId);
        signBody.setUserId((int) userBean.getId());
        binding.sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(signBody.getShopImage())) {
                    String toastStr = getString(R.string.toastStr10);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String remark = binding.etRemark.getText().toString();
                if (TextUtils.isEmpty(remark)) {
                    String toastStr = getString(R.string.toastStr11);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                signBody.setRemarks(remark);
                HttpUtil.getInstance().shopSign(signBody).subscribe(
                        str -> {
                            String toastStr = getString(R.string.toastStr12);
                            Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        }
                );
            }
        });


        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            @SuppressLint("CheckResult")
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
                            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                            binding.img.setBackground(bitmapDrawable);
                            signBody.setShopImage(path);
                        }
                );

            }
        }).intent(intent).activity((AppCompatActivity) getActivity()).build().startActivityForResult();

    }
}
