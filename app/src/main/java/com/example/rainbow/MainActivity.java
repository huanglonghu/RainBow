package com.example.rainbow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.constant.HttpParam;
import com.example.rainbow.handler.ActivityResultHandler;
import com.example.rainbow.language.LanguagesManager;
import com.example.rainbow.net.HttpUtil;
import com.example.rainbow.ui.fragment.Login;
import com.example.rainbow.ui.fragment.MainFragment;
import com.example.rainbow.util.LogUtil;
import com.example.rainbow.util.statusBarHandler.StatusBarUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucentStatus(this);
        FragmentManager fm = getSupportFragmentManager();
        new Presenter.Builder().context(this).fragmentManager(fm).build();
        HttpUtil.getInstance().init(this);
        LogUtil.log("==========tokendd==========="+HttpParam.token);
        if(TextUtils.isEmpty(HttpParam.token)){
            Login login = new Login();
            fm.beginTransaction().replace(R.id.activity_container, login).commit();
        }else {
            MainFragment mainFragment = new MainFragment();
            Presenter.getInstance().step2MainFragment("main",mainFragment);
        }
        AndPermission.with(this)
                .runtime()
                .permission(Permission.WRITE_EXTERNAL_STORAGE, Permission.CAMERA)
                .onGranted(permissions -> {
                    // Storage permission are allowed.
                })
                .onDenied(permissions -> {
                    // Storage permission are not allowed.
                })
                .start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ActivityResultHandler.getInstance().handler(requestCode, resultCode, data);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesManager.attach(newBase));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }


}