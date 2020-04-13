package com.example.rainbow.handler;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.rainbow.strategy.HandlerStrategy;
import com.example.rainbow.util.FileUtil;
import com.example.rainbow.util.ImagUtil;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ActivityResultHandler {
    // 处理各种界面跳转的问题
    private FragmentManager fragmentManager;
    public static final int REQUEST_SELECT_CONTACTS = 100;//联系人
    public static final int REQUEST_SELECT_PHOTO_CROP = 101;//选择图片
    public static final int REQUEST_CROP_PHOTO = 102; //裁剪图片呢
    public static final int REQUEST_ANALY_PHOTO = 103; //解析图片
    public static final int REQUEST_SCAN = 104;//扫一扫
    public static final int REQUEST_BACK = 105;
    public static final int REQUEST_TAKE_PHOTO = 106;
    public static final int REQUEST_SELECT_PHOTO_CHAT = 107;
    public static final int REQUEST_SELECT_PHOTO = 108;//选择图片

    private int RESULT_OK = 0xA1;
    private Intent intent;
    private int requestCode;
    private AppCompatActivity activity;
    private HandlerStrategy handlerStrategy;

    private ActivityResultHandler() {
    }

    private static ActivityResultHandler defaultInstance;

    public static ActivityResultHandler getInstance() {
        ActivityResultHandler activityResultHandler = defaultInstance;
        if (defaultInstance == null) {
            synchronized (ActivityResultHandler.class) {
                if (defaultInstance == null) {
                    activityResultHandler = new ActivityResultHandler();
                    defaultInstance = activityResultHandler;
                }
            }
        }
        return activityResultHandler;
    }

    private void init(Builder builder) {
        this.activity = builder.activity;
        this.intent = builder.intent;
        this.requestCode = builder.requestCode;
        this.handlerStrategy = builder.handlerStrategy;
        fragmentManager = activity.getSupportFragmentManager();
    }

    private void init(AppCompatActivity activity) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
    }


    public void startActivityForResult() {
        activity.startActivityForResult(intent, requestCode);
    }

    public static class Builder {
        private Intent intent;
        private int requestCode;
        private AppCompatActivity activity;
        private HandlerStrategy handlerStrategy;

        public Builder intent(Intent intent) {
            this.intent = intent;
            return this;
        }

        public Builder requestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public Builder activity(AppCompatActivity activity) {
            this.activity = activity;
            return this;
        }

        public Builder hadlerStrategy(HandlerStrategy handlerStrategy) {
            this.handlerStrategy = handlerStrategy;
            return this;
        }

        public ActivityResultHandler build() {
            getInstance().init(this);
            return defaultInstance;
        }

    }


    public void handler(int requestCode, int resultCode, Intent data) {
        Uri uri = null;
        if (data != null) {
            uri = data.getData();
        }
        switch (requestCode) {
            case REQUEST_SELECT_PHOTO: {
                if (uri == null) {
                    return;
                }
                String cropImagePath = FileUtil.getRealFilePathFromUri(activity, uri);
                Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                File file = new File(cropImagePath);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                handlerStrategy.onActivityResult(filePart, bitMap);
            }
            break;
            case REQUEST_SELECT_PHOTO_CROP: {
                if (uri == null) {
                    return;
                }
                String cropImagePath = FileUtil.getRealFilePathFromUri(activity, uri);
                Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                File file = new File(cropImagePath);
                int max = 5 * 1024 * 1024;
                if (bitMap.getByteCount() >= max) {
                    file = ImagUtil.compressImage(file, bitMap);
                }
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));
                handlerStrategy.onActivityResult(filePart, bitMap);
            }

            break;
            case REQUEST_CROP_PHOTO:
                if (uri == null) {
                    return;
                }
                String cropImagePath = FileUtil.getRealFilePathFromUri(activity, uri);
                Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                File file = new File(cropImagePath);
                MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file));

                handlerStrategy.onActivityResult(filePart, bitMap);
                break;
            case REQUEST_TAKE_PHOTO: {
                handlerStrategy.onActivityResult();
            }
            break;
            case REQUEST_SELECT_PHOTO_CHAT:
                if (data != null) {
                    String path = data.getStringExtra("path");
                    handlerStrategy.onActivityResult(path);
                }
                break;


        }

    }


}
