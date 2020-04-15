package com.example.rainbow.ui.fragment.task;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.UpdateDepositBody;
import com.example.rainbow.bean.UploadPictureResponse;
import com.example.rainbow.databinding.FragmentDepositBinding;
import com.example.rainbow.net.HttpInterface;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.widget.NetLoading;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import java.io.File;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Deposit extends BaseFragment {

    private FragmentDepositBinding binding;
    private Double yj;
    private int shopId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deposit, container, false);
        initlisten();
        initData();
        return binding.getRoot();
    }

    @Override
    public void initData() {
        Bundle bundle = getArguments();
        yj = bundle.getDouble("yj");
        shopId = bundle.getInt("shopId");
        binding.setYj(yj);
        binding.yj.setText(yj + "");
        binding.etYj.setText(yj + "");
    }

    @Override
    public void initView() {
    }

    @Override
    public void initlisten() {
        binding.feduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yj >= 100) {
                    yj = yj - 100;
                    binding.setYj(yj);
                }
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yj = yj + 100;
                binding.setYj(yj);
            }
        });


        binding.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.signArea.clear();
            }
        });

        String depositStr = binding.etYj.getText().toString();
        binding.commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(depositStr)) {
                    yj = Double.parseDouble(depositStr);
                }
                if (!binding.signArea.isEmpty()) {
                    try {
                        View root = binding.getRoot();
                        Bitmap bitmap2 = Bitmap.createBitmap(root.getWidth(), root.getHeight(), Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas();
                        canvas.setBitmap(bitmap2);
                        root.draw(canvas);
                        File file = Presenter.getInstance().save(getContext(), bitmap2);
                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                        Observable<String> uploadPicture = createUploadPicture(filePart);
                        NetLoading netLoading = new NetLoading(getContext());
                        netLoading.show();
                        Observable.mergeArray(uploadPicture).flatMap(new Function<String, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(String s) throws Exception {
                                UpdateDepositBody updateDepositBody = new UpdateDepositBody();
                                updateDepositBody.setDeposit(yj);
                                updateDepositBody.setShopId(shopId);
                                updateDepositBody.setSign(s);
                                Observable<String> observable = createObservable(updateDepositBody);
                                return observable;
                            }
                        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                                str -> {
                                    netLoading.dismiss();
                                    binding.newDeposit.setText(yj + "");
                                    String toastStr = getString(R.string.toastStr42);
                                    Toast.makeText(getContext(), toastStr, Toast.LENGTH_SHORT).show();
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


    private Observable<String> createUploadPicture(MultipartBody.Part part) {
        Call<ResponseBody> call = HttpUtil.getInstance().getRetrofit().create(HttpInterface.class).uploadPicture(part);
        return Observable.create(new ObservableOnSubscribe<String>() {
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


    public Observable<String> createObservable(UpdateDepositBody body) {
        Call<ResponseBody> call = HttpUtil.getInstance().getRetrofit().create(HttpInterface.class).updateDeposit(body);
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


}
