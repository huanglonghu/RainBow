package com.example.rainbow.net;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.rainbow.R;
import com.example.rainbow.base.Presenter;
import com.example.rainbow.bean.EnterBody;
import com.example.rainbow.bean.ErrorBody;
import com.example.rainbow.bean.HandlerFaultBody;
import com.example.rainbow.bean.MachineGuideBody;
import com.example.rainbow.bean.MachineSettleBody;
import com.example.rainbow.bean.RouteSettleBody;
import com.example.rainbow.bean.ShopBody;
import com.example.rainbow.bean.ShopSettleBody;
import com.example.rainbow.bean.SignBody;
import com.example.rainbow.constant.HttpParam;
import com.example.rainbow.database.entity.UserBean;
import com.example.rainbow.database.option.UserOption;
import com.example.rainbow.ui.widget.NetLoading;
import com.example.rainbow.util.GsonUtil;
import com.example.rainbow.util.LogUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Part;

public class HttpUtil {
    private HttpInterface httpInterface;
    private HttpLoggingInterceptor loggingInterceptor;
    private Retrofit retrofit;
    private OkHttpClient client;
    private NetLoading netLoading;

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void setRetrofit(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    private HttpUtil() {
        //打印retrofit日志
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                LogUtil.log(message);
            }
        });

        Interceptor interceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl oldHttpUrl = request.url();
                Request.Builder builder = request.newBuilder();
                String path = oldHttpUrl.encodedPath();
                if (!path.contains("userlogin")) {
                    UserBean userBean = UserOption.getInstance().querryUser();
                    if (userBean != null) {
                        String token = userBean.getToken();
                        if (!TextUtils.isEmpty(token)) {
                            request = builder.addHeader("Authorization", "Bearer " + token).build();
                            LogUtil.log("==============token============" + token);
                        }
                    }
                }

                okhttp3.Response response = chain.proceed(request);
                if (response.code() == 401) {
                    UserBean userBean = UserOption.getInstance().querryUser();
                    if (userBean != null) {
                        UserOption.getInstance().delteUser();
                    }
                }
                return response;


            }
        };


        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS).addInterceptor(loggingInterceptor).addInterceptor(interceptor)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS).build();
        retrofit = new Retrofit.Builder().client(client).baseUrl(HttpParam.baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        httpInterface = retrofit.create(HttpInterface.class);
    }

    private static HttpUtil defauleInstance;

    public static HttpUtil getInstance() {
        HttpUtil httpUtil = defauleInstance;
        if (defauleInstance == null) {
            synchronized (HttpUtil.class) {
                httpUtil = new HttpUtil();
                defauleInstance = httpUtil;
            }
        }
        return httpUtil;
    }

    private Context context;

    public void init(Context context) {
        this.context = context;
    }


    public Observable<String> getMachineRoomUserMsg() {
        Call<ResponseBody> call = httpInterface.getJSUserMsg();
        return enqueueCall(call);
    }


    public Observable<String> getLxUserMsg() {
        Call<ResponseBody> call = httpInterface.getLxUserMsg();
        return enqueueCall(call);
    }

    public Observable<String> editeditMachineRoomUserPwd(String oldPassword, String passWord) {
        HashMap<String, String> map = new HashMap<>();
        map.put("oldPassWord", oldPassword);
        map.put("passWord", passWord);
        Call<ResponseBody> call = httpInterface.editMachineRoomUserPwd(map);
        return enqueueCall(call);
    }

    public Observable<String> editRouterPwd(String oldPassword, String passWord) {
        HashMap<String, String> map = new HashMap<>();
        map.put("oldPassWord", oldPassword);
        map.put("passWord", passWord);
        Call<ResponseBody> call = httpInterface.editRouterPwd(map);
        return enqueueCall(call);
    }


    public Observable<String> login(String userName, String pwd) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userName", userName);
        map.put("passWord", pwd);
        Call<ResponseBody> call = httpInterface.login(map);
        return enqueueCall(call);
    }

    public Observable<String> enterXf(EnterBody enterBody) {
        Call<ResponseBody> call = httpInterface.enterXF(enterBody);
        return enqueueCall(call);
    }


    public Observable<String> querryMachines() {
        Call<ResponseBody> call = httpInterface.querryMachine();
        return enqueueCall(call);
    }


    public Observable<String> querryXFRecord(int page) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("limit", 10);
        map.put("page", page);
        Call<ResponseBody> call = httpInterface.querryXFRecord(map);
        return enqueueCall(call);
    }

    public Observable<String> getClockInRecord(String startDate, String endDate, int page) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("page", page);
        map.put("limit", 10);
        Call<ResponseBody> call = httpInterface.getClockInRecord(map);
        return enqueueCall(call);
    }

    public Observable<String> getNoticeList(int page) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", 10);
        Call<ResponseBody> call = httpInterface.getNoticeList(map);
        return enqueueCall(call);
    }

    public Observable<String> querryRoutes(int page) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("limit", 10);
        Call<ResponseBody> call = httpInterface.querryRoutes(map);
        return enqueueCall(call);
    }

    public Observable<String> getJob(int missionType) {
        Call<ResponseBody> call = httpInterface.getJob(missionType);
        return enqueueCall(call);
    }

    public Observable<String> getJobDetail(int id) {
        Call<ResponseBody> call = httpInterface.getJobDetail(id);
        return enqueueCall(call);
    }

    public Observable<String> getRouteWinlostDetail(int id) {
        Call<ResponseBody> call = httpInterface.getRouteWinlostDetail(id);
        return enqueueCall(call);
    }

    public Observable<String> routeSettle(RouteSettleBody body) {
        Call<ResponseBody> call = httpInterface.routeSettle(body);
        return enqueueCall(call);
    }

    HashMap<Call<ResponseBody>, NetLoading> map = new HashMap<>();

    @NonNull
    private Observable<String> enqueueCall(final Call<ResponseBody> call) {
        netLoading = new NetLoading(context);
        netLoading.show();
        map.put(call, netLoading);
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                a(observableEmitter, call);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<String> getShopDetail(int id, int businessId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("businessId", businessId);
        map.put("id", id);
        Call<ResponseBody> call = httpInterface.getShopDetail(map);
        return enqueueCall(call);
    }

    public Observable<String> getShopfaultDetail(int id, int businessId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("businessId", businessId);
        map.put("id", id);
        Call<ResponseBody> call = httpInterface.getShopfaultDetail(map);
        return enqueueCall(call);
    }


    public Observable<String> toWork() {//上班打卡
        Call<ResponseBody> call = httpInterface.toWork();
        return enqueueCall(call);
    }


    public Observable<String> offWork() {//下班打卡
        Call<ResponseBody> call = httpInterface.offWork();
        return enqueueCall(call);
    }


    public Observable<String> getMachineDetail(int id, int businessId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("businessId", businessId);
        map.put("id", id);
        Call<ResponseBody> call = httpInterface.getMachineDetail(map);
        return enqueueCall(call);
    }

    public Observable<String> getMachineFaultDeail(int id) {
        Call<ResponseBody> call = httpInterface.getMachineFaultDeail(id);
        return enqueueCall(call);
    }


    public Observable<String> shopSign(SignBody signBody) {
        Call<ResponseBody> call = httpInterface.shopSign(signBody);
        return enqueueCall(call);
    }

    public Observable<String> shopSettle(ShopSettleBody body) {
        Call<ResponseBody> call = httpInterface.shopSettle(body);
        return enqueueCall(call);
    }

    public Observable<String> getShopWinloss(int id, int businessId) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("businessId", businessId);
        map.put("id", id);
        Call<ResponseBody> call = httpInterface.getShopWinloss(map);
        return enqueueCall(call);
    }

    public Observable<String> uploadFault(int machineId, String faultImage, String describe) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("machineId", machineId);
        map.put("faultImage", faultImage);
        map.put("faultDescribe", describe);
        Call<ResponseBody> call = httpInterface.uploadFault(map);
        return enqueueCall(call);
    }


    public Observable<String> handleFault(HandlerFaultBody body) {
        Call<ResponseBody> call = httpInterface.handleFault(body);
        return enqueueCall(call);
    }


    public Observable<String> uploadPicture(@Part MultipartBody.Part file) {
        Call<ResponseBody> call = httpInterface.uploadPicture(file);
        return enqueueCall(call);
    }

    public Observable<String> getNoticeDetailById(int id) {
        Call<ResponseBody> call = httpInterface.getNoticeDetailById(id);
        return enqueueCall(call);
    }

    public Observable<String> getWxznList(MachineGuideBody body) {
        Call<ResponseBody> call = httpInterface.getWxznList(body);
        return enqueueCall(call);
    }

    public Observable<String> getWxznDetailById(int id) {

        Call<ResponseBody> call = httpInterface.getWxznDetailById(id);
        return enqueueCall(call);
    }


    public Observable<String> shopSettle(ShopBody shopBody) {
        Call<ResponseBody> call = httpInterface.shopSettle(shopBody);
        return enqueueCall(call);
    }

    public Observable<String> machineSettle(MachineSettleBody machineSettleBody) {
        Call<ResponseBody> call = httpInterface.machineSettle(machineSettleBody);
        return enqueueCall(call);
    }

    private void a(final ObservableEmitter<String> observableEmitter, final Call<ResponseBody> call) {
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call1, Response<ResponseBody> response) {
                if (map.get(call) != null) {
                    NetLoading netLoading = map.get(call);
                    netLoading.cancel();
                    netLoading = null;
                    map.remove(netLoading);
                }
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        if (body.contains("\"code\":0")) {
                            observableEmitter.onNext(body);
                        } else {
                            try {
                                JSONObject jb = new JSONObject(body);
                                String message = jb.getString("msg");
                                int errcode = jb.getInt("code");
                                //5008 楼盘未绑定经纪人 1009 尚未注册
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {

                            }
                        }
                    } else {
                        String body = response.errorBody().string();
                        try {
                            JSONObject jb = new JSONObject(body);
                            String message = jb.getString("msg");
                            int errcode = jb.getInt("code");
                            //5008 楼盘未绑定经纪人 1009 尚未注册
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call1, Throwable t) {
                String netError = context.getString(R.string.netError);
                Toast.makeText(context, netError, Toast.LENGTH_SHORT).show();
                if (map.get(call) != null) {
                    NetLoading netLoading = map.get(call);
                    netLoading.cancel();
                    netLoading = null;
                    map.remove(netLoading);
                }
            }
        });
    }


}
