package com.example.rainbow.bean;

public class ShopSettleBody {


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
    private double shopDeductionMoney;
    private double shopPayMoney;
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

    public double getShopDeductionMoney() {
        return shopDeductionMoney;
    }

    public void setShopDeductionMoney(double shopDeductionMoney) {
        this.shopDeductionMoney = shopDeductionMoney;
    }

    public double getShopPayMoney() {
        return shopPayMoney;
    }

    public void setShopPayMoney(double shopPayMoney) {
        this.shopPayMoney = shopPayMoney;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
