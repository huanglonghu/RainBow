package com.example.rainbow;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.rainbow.base.BaseFragment;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.fragment.Login;
import com.example.rainbow.ui.fragment.MainFragment;
import com.example.rainbow.util.statusBarHandler.StatusBarUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentStatus(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        new Presenter.Builder().context(this).fragmentManager(fm).build();
        HttpUtil.getInstance().init(this);
        Login login = new Login();
        fm.beginTransaction().replace(R.id.activity_container, login).commit();
        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE,Permission.CAMERA)
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                })
                .start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ActivityResultHandler.getInstance().handler(requestCode, resultCode, data);
    }
}