package com.example.rainbow.base;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.example.rainbow.database.DaoMaster;
import com.example.rainbow.database.DaoSession;
import com.example.rainbow.language.LanguagesManager;
import com.example.rainbow.util.LogUtil;
import com.example.rainbow.util.RudenessScreenHelper;
import java.util.Locale;

public class RainBowApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        float designWidth = 1920;
        new RudenessScreenHelper(this, designWidth).activate();
        setDatabase();
        LanguagesManager.init(this);
        Locale systemLanguage = LanguagesManager.getSystemLanguage();
        LogUtil.log("==============system=============="+systemLanguage.getCountry());
        LanguagesManager.setAppLanguage(this, systemLanguage);
    }


    private DaoSession mDaoSession;
    private SQLiteDatabase db;


    public int getWindowWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int windownWidth = metric.widthPixels;
        return windownWidth;
    }

    public int getWindowHeight() {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int windowHeight = metric.heightPixels;
        return windowHeight;
    }


    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);

        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }


    private static RainBowApplication application;

    public static RainBowApplication getApplication() {
        return application;
    }


    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesManager.attach(base));
    }


}
