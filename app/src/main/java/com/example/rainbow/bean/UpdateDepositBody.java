package com.example.rainbow.bean;

public class UpdateDepositBody {


    /**
     * shopId : 0
     * deposit : 0
     * sign : string
     */

    private int shopId;
    private double deposit;
    private String sign;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
