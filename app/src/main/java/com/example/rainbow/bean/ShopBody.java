package com.example.rainbow.bean;

public class ShopBody {


    /**
     * jobId : 0
     * shopId : 0
     * shopWashScore : 0
     * shopDeductionMoney : 0
     * shopPayMoney : 0
     * sign : string
     */

    private int jobId;
    private int shopId;
    private int shopWashScore;
    private int shopDeductionMoney;
    private int shopPayMoney;
    private String sign;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getShopWashScore() {
        return shopWashScore;
    }

    public void setShopWashScore(int shopWashScore) {
        this.shopWashScore = shopWashScore;
    }

    public int getShopDeductionMoney() {
        return shopDeductionMoney;
    }

    public void setShopDeductionMoney(int shopDeductionMoney) {
        this.shopDeductionMoney = shopDeductionMoney;
    }

    public int getShopPayMoney() {
        return shopPayMoney;
    }

    public void setShopPayMoney(int shopPayMoney) {
        this.shopPayMoney = shopPayMoney;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
