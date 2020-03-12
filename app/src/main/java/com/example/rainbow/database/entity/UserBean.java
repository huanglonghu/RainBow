package com.example.rainbow.database.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserBean {

    private String nickName;
    @Id
    private long id;
    private int userType;
    private String mobile;
    private int shopId;
    private String shopName;
    private String token;
    @Generated(hash = 594836297)
    public UserBean(String nickName, long id, int userType, String mobile,
            int shopId, String shopName, String token) {
        this.nickName = nickName;
        this.id = id;
        this.userType = userType;
        this.mobile = mobile;
        this.shopId = shopId;
        this.shopName = shopName;
        this.token = token;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getUserType() {
        return this.userType;
    }
    public void setUserType(int userType) {
        this.userType = userType;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public int getShopId() {
        return this.shopId;
    }
    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
    public String getShopName() {
        return this.shopName;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }


}
