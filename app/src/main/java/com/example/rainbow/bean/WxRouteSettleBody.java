package com.example.rainbow.bean;

public class WxRouteSettleBody {


    /**
     * jobId : 0
     * hotelMoney : 0
     * fuelMoney : 0
     * eatMoney : 0
     * otherMoney : 0
     * remarks : string
     * sign : string
     */

    private int jobId;
    private double hotelMoney;
    private double fuelMoney;
    private double eatMoney;
    private double otherMoney;
    private String remarks;
    private String sign;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public double getHotelMoney() {
        return hotelMoney;
    }

    public void setHotelMoney(double hotelMoney) {
        this.hotelMoney = hotelMoney;
    }

    public double getFuelMoney() {
        return fuelMoney;
    }

    public void setFuelMoney(double fuelMoney) {
        this.fuelMoney = fuelMoney;
    }

    public double getEatMoney() {
        return eatMoney;
    }

    public void setEatMoney(double eatMoney) {
        this.eatMoney = eatMoney;
    }

    public double getOtherMoney() {
        return otherMoney;
    }

    public void setOtherMoney(double otherMoney) {
        this.otherMoney = otherMoney;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
