package com.example.rainbow.net;

import com.example.rainbow.bean.EnterBody;
import com.example.rainbow.bean.HandlerFaultBody;
import com.example.rainbow.bean.MachineGuideBody;
import com.example.rainbow.bean.MachineSettleBody;
import com.example.rainbow.bean.RouteSettleBody;
import com.example.rainbow.bean.ShopBody;
import com.example.rainbow.bean.ShopSettleBody;
import com.example.rainbow.bean.SignBody;
import com.example.rainbow.bean.WxRouteSettleBody;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface HttpInterface {

    @POST("/api/v1/tokenauth/userlogin")
    Call<ResponseBody> login(@Body HashMap<String, String> map);

    @GET("/api/v1/routeuser/appget")
    Call<ResponseBody> getLxUserMsg();//获取路线员工个人信息

    @GET(" /api/v1/machineroomuser/appget")
    Call<ResponseBody> getJSUserMsg();//获取机室员工个人信息

    @POST("/api/v1/routeuser/appupdatepassword")
    Call<ResponseBody> editRouterPwd(@Body HashMap<String, String> map);

    @POST("/api/v1/machineroomuser/appupdatepassword")
    Call<ResponseBody> editMachineRoomUserPwd(@Body HashMap<String, String> map);

    @GET("/api/v1/clockinrecord/gooffwork")
        //机室员工下班打卡
    Call<ResponseBody> offWork();

    @GET("/api/v1/clockinrecord/gotowork")
        //机室员工下班打卡
    Call<ResponseBody> toWork();

    @GET("/api/v1/machine/appquerymachinename")
    Call<ResponseBody> querryMachine();

    @POST("/api/v1/machineloss/create")
    Call<ResponseBody> enterXF(@Body EnterBody enterBody);

    @POST("/api/v1/machineloss/querypage")
    Call<ResponseBody> querryXFRecord(@Body HashMap<String, Integer> map);

    @POST("/api/v1/clockinrecord/appquerypage")
    Call<ResponseBody> getClockInRecord(@Body HashMap<String, Object> map);

    @POST("/api/v1/notice/appquerypage")
    Call<ResponseBody> getNoticeList(@Body HashMap<String, Integer> map);

    @POST("/api/v1/route/querypage")
    Call<ResponseBody> querryRoutes(@Body HashMap<String, Object> map);

    @GET("/api/v1/job/appgetongoingjob")
    Call<ResponseBody> getJob(@Query("missionType") int missionType);

    @GET("/api/v1/job/appgetjobdetails")
    Call<ResponseBody> getJobDetail(@Query("id") int id);

    @GET("/api/v1/jobrouteprofitloss/appgetrouteprofitlossbyjob")
    Call<ResponseBody> getRouteWinlostDetail(@Query("id") int id);


    @POST("/api/v1/jobrouteprofitloss/create")
    Call<ResponseBody> routeSettle(@Body RouteSettleBody body);


    @POST("/api/v1/job/appgetjobshopprofitlossdetails")
    Call<ResponseBody> getShopDetail(@Body HashMap<String, Integer> map);

    @POST("/api/v1/job/appgetjobshopdetails")
    Call<ResponseBody> getShopfaultDetail(@Body HashMap<String, Integer> map);


    @POST("/api/v1/job/appgetjobmachinedetails")
    Call<ResponseBody> getMachineDetail(@Body HashMap<String, Integer> map);

    @GET("/api/v1/machinefault/get")
    Call<ResponseBody> getMachineFaultDeail(@Query("id") int id);

    @POST("/api/v1/shopsignin/create")
    Call<ResponseBody> shopSign(@Body SignBody body);

    @POST("/api/v1/jobshopprofitloss/create")
    Call<ResponseBody> shopSettle(@Body ShopSettleBody body);

    @POST("/api/v1/shopprofitloss/appgetshopprofitlossbyjob")
    Call<ResponseBody> getShopWinloss(@Body HashMap<String, Integer> map);

    @POST("/api/v1/machinefault/create")
    Call<ResponseBody> uploadFault(@Body HashMap<String, Object> map);

    @POST("/api/v1/machinefault/update")
    Call<ResponseBody> handleFault(@Body HandlerFaultBody handlerFaultBody);

    @POST("/api/v1/jobshopprofitloss/create")
    Call<ResponseBody> shopSettle(@Body ShopBody body);

    @POST("/api/v1/machineprofitloss/create")
    Call<ResponseBody> machineSettle(@Body MachineSettleBody body);

    @Multipart
    @POST("/api/v1/img/insertpicture")
    Call<ResponseBody> uploadPicture(@Part MultipartBody.Part file);

    @GET("/api/v1/notice/get")
    Call<ResponseBody> getNoticeDetailById(@Query("id") int id);

    @POST("/api/v1/machinesguide/querypage")
    Call<ResponseBody> getWxznList(@Body MachineGuideBody body);

    @GET("/api/v1/machinesguide/get")
    Call<ResponseBody> getWxznDetailById(@Query("id") int id);


    @POST("/api/v1/jobrouteprofitloss/repaircreate")
    Call<ResponseBody> wxRouteSettle(@Body WxRouteSettleBody wxRouteSettleBody);


}