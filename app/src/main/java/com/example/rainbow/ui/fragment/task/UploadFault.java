package com.example.rainbow.ui.fragment.task;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.base.RainBowApplication;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentUploadFaultBinding;
import com.example.rainbow.databinding.ImgItemBinding;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpInterface;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.ui.main.Task;
import com.example.rainbow.ui.widget.NetLoading;
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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static okhttp3.MultipartBody.*;

public class UploadFault extends BaseFragment {

    private FragmentUploadFaultBinding binding;
    private int machineId;
    private ArrayList<String> pathList;
    private int windowWidth;
    private NetLoading netLoading;
    private Task task;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        task = (Task) getParentFragment();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_upload_fault, container, false);
        initView();
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
        windowWidth = RainBowApplication.getApplication().getWindowWidth();
        parts = new ArrayList<>();
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parts.isEmpty()) {
                    String toastStr = getString(R.string.toastStr15);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                String faultDescribe = binding.faultDescribe.getText().toString();
                if (TextUtils.isEmpty(faultDescribe)) {
                    String toastStr = getString(R.string.toastStr17);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                upload(faultDescribe);
            }
        });

        binding.takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parts.size() == 4) {
                    String toastStr = getString(R.string.toastStr22);
                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                    return;
                }
                goCamera();
            }
        });


    }


    private ArrayList<MultipartBody.Part> parts;

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
                final Bitmap bitmap = BitmapFactory.decodeFile(photoPath);
                if (bitmap != null) {
                    int w = (windowWidth * 305) / 1920;
                    int h = (windowWidth * 215) / 1920;
                    int w2 = (windowWidth * 290) / 1920;
                    int h2 = (windowWidth * 200) / 1920;
                    int s = (windowWidth * 30) / 1920;
                    ImgItemBinding imgItemBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.img_item, binding.photoContainer, false);
                    LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(w, h);
                    RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(w2, h2);
                    RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(s, s);
                    lp2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    lp3.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    View item = imgItemBinding.getRoot();
                    item.setLayoutParams(lp1);
                    imgItemBinding.photo.setLayoutParams(lp2);
                    imgItemBinding.delete.setLayoutParams(lp3);
                    imgItemBinding.photo.setImageDrawable(new BitmapDrawable(bitmap));
                    binding.photoContainer.addView(item);
                    Part filePart = Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

                    parts.add(filePart);
                    imgItemBinding.delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            bitmap.recycle();
                            parts.remove(filePart);
                            binding.photoContainer.removeView(item);
                        }
                    });

                }
            }
        }).intent(intent).activity((AppCompatActivity) getActivity()).build().startActivityForResult();

    }


    private String TAG = "UPLOAD";

    private void upload(String faultDescribe) {
        Observable<String>[] observables = new Observable[parts.size()];
        for (int i = 0; i < parts.size(); i++) {
            MultipartBody.Part part = parts.get(i);
            createUploadPicture(observables, i, part);

        }
        netLoading = new NetLoading(getContext());
        netLoading.show();
        Observable.mergeArray(observables).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                pathList.add(s);
                if (pathList.size() == parts.size()) {
                    HashMap<String, Object> map = merageMap(faultDescribe);
                    Observable<String> observable = createObservable(map);
                    return observable;
                } else {
                    return Observable.just("");
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                a -> {
                    netLoading.dismiss();
                    if (!TextUtils.isEmpty(a)) {
                        String toastStr = getString(R.string.toastStr18);
                        Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
                        task.back();
                    }
                }
        );


    }

    private void createUploadPicture(Observable<String>[] observables, int i, Part part) {
        Call<ResponseBody> call = HttpUtil.getInstance().getRetrofit().create(HttpInterface.class).uploadPicture(part);
        observables[i] = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String body = response.body().string();
                                UploadPictureResponse uploadPictureResponse = GsonUtil.fromJson(body, UploadPictureResponse.class);
                                String data = uploadPictureResponse.getData();
                                observableEmitter.onNext(data);
                            } catch (Exception e) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });
    }


    public Observable<String> createObservable(HashMap<String, Object> map) {
        Call<ResponseBody> call = HttpUtil.getInstance().getRetrofit().create(HttpInterface.class).uploadFault(map);
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String body = response.body().string();
                                observableEmitter.onNext(body);
                            } catch (Exception e) {
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        });


    }


    public HashMap<String, Object> merageMap(String faultDescribe) {
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
        HashMap<String, Object> map = new HashMap<>();
        map.put("machineId", machineId);
        map.put("faultImage", sb.toString());
        map.put("faultDescribe", faultDescribe);
        return map;
    }


}
