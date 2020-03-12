package com.example.rainbow.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.ui.main.ClockIn;
import com.example.rainbow.ui.fragment.EditPwd;
import com.example.rainbow.ui.fragment.MainFragment;
import com.example.rainbow.ui.fragment.NoticeList;
import com.example.rainbow.ui.fragment.PersonalData;
import com.example.rainbow.ui.fragment.task.CollectionManagement;
import com.example.rainbow.ui.fragment.task.FaultDetail;
import com.example.rainbow.ui.fragment.task.LineSelect;
import com.example.rainbow.ui.fragment.task.Machine;
import com.example.rainbow.ui.fragment.task.Shop;
import com.example.rainbow.ui.fragment.task.ShopSettle;
import com.example.rainbow.ui.fragment.task.Signed;
import com.example.rainbow.ui.fragment.task.TaskSelect;
import com.example.rainbow.ui.fragment.task.UploadFault;
import com.example.rainbow.ui.fragment.task.WinLostRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.FragmentManager;

public class Presenter {

    private FragmentManager fm;
    private FragmentManager mainFm;
    private FragmentManager taskFm;
    private FragmentManager personDetailFm;

    public FragmentManager getPersonDetailFm() {
        return personDetailFm;
    }

    public void setPersonDetailFm(FragmentManager personDetailFm) {
        this.personDetailFm = personDetailFm;
    }

    public FragmentManager getMainFm() {
        return mainFm;
    }

    public void setMainFm(FragmentManager mainFm) {
        this.mainFm = mainFm;
    }


    private Presenter() {
        fragmentMap = new HashMap<>();
    }

    private static Presenter defaultInstance;

    public static Presenter getInstance() {
        Presenter presenter = defaultInstance;
        if (defaultInstance == null) {
            synchronized (Presenter.class) {
                if (defaultInstance == null) {
                    presenter = new Presenter();
                    defaultInstance = presenter;
                }
            }
        }
        return presenter;
    }

    public FragmentManager getTaskFm() {
        return taskFm;
    }

    public void setTaskFm(FragmentManager taskFm) {
        this.taskFm = taskFm;
    }


    public void startNaviGoogle(double la, double lon) {
        if (isAvilible(context, "com.google.android.apps.maps")) {
            Uri gmmIntentUri = Uri.parse("google.navigation:q=" + la + "," + lon);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        } else {
            Toast.makeText(context, "您尚未安装谷歌地图或地图版本过低", Toast.LENGTH_SHORT).show();
        }
    }

    //验证各种导航地图是否安装
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


    public void step2MainFragment(String name) {
        fm.beginTransaction().replace(R.id.activity_container, getFragment(name)).commit();
    }


    private HashMap<String, BaseFragment> fragmentMap;

    public BaseFragment getFragment(String name) {
        if (fragmentMap.get(name) == null) {
            BaseFragment fragment = null;
            switch (name) {
                case "clockIn":
                    fragment = new ClockIn();
                    break;
                case "personData":
                    fragment = new PersonalData();
                    break;
                case "notice":
                    fragment = new NoticeList();
                    break;
                case "skgl":
                    fragment = new CollectionManagement();
                    break;
                case "shop":
                    fragment = new Shop();
                    break;
                case "machine":
                    fragment = new Machine();
                    break;
                case "uploadFault":
                    fragment = new UploadFault();
                    break;
                case "shopSettle":
                    fragment = new ShopSettle();
                    break;
                case "winLost":
                    fragment = new WinLostRecord();
                    break;
                case "faultDetail":
                    fragment = new FaultDetail();
                    break;
                case "lineSelect":
                    fragment = new LineSelect();
                    break;
                case "taskSelect":
                    fragment = new TaskSelect();
                    break;
                case "main":
                    fragment = new MainFragment();
                    break;
                case "editPwd":
                    fragment = new EditPwd();
                    break;
                case "sign":
                    fragment = new Signed();
                    break;
            }
            fragmentMap.put(name, fragment);
            return fragment;
        } else {
            return fragmentMap.get(name);
        }
    }

    public void back() {
        taskFm.popBackStack();
    }

    private Context context;

    private void init(Builder builder, Context context) {
        this.fm = builder.fm;
        this.context = context;
    }


    public static class Builder {
        private FragmentManager fm;
        private Context context;

        public Builder fragmentManager(FragmentManager fm) {
            this.fm = fm;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }


        public Presenter build() {
            getInstance().init(this, context);
            return defaultInstance;
        }
    }


}
