package com.example.rainbow.bean;

public class SignBody {


    /**
     * jobId : 0
     * userId : 0
     * shopId : 0
     * shopImage : string
     * remarks : string
     */

    private int jobId;
    private int userId;
    private int shopId;
    private String shopImage;
    private String remarks;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
