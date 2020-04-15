package com.example.rainbow.bean;

public class ShopSettledResponse {


    /**
     * code : 0
     * msg : success
     * data : {"jobId":1,"shopId":3,"profitLossId":2,"totalProfitLoss":0,"splitRatio":0,"actualWashScore":0,"businessProfitLoss":0,"platformProfitLoss":0,"shopWashScore":1300,"washScoreProfit":0,"shopDeductionMoney":0,"shopPayMoney":900,"shopArrears":0,"platformIncome":-600,"isSettled":true,"sign":"images\\23397f80a825438a92fa07a9e29c444d.png","creationTime":"2020-04-15T15:13:59.943","id":2}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * jobId : 1
         * shopId : 3
         * profitLossId : 2
         * totalProfitLoss : 0.0
         * splitRatio : 0.0
         * actualWashScore : 0
         * businessProfitLoss : 0.0
         * platformProfitLoss : 0.0
         * shopWashScore : 1300
         * washScoreProfit : 0.0
         * shopDeductionMoney : 0.0
         * shopPayMoney : 900.0
         * shopArrears : 0.0
         * platformIncome : -600.0
         * isSettled : true
         * sign : images\23397f80a825438a92fa07a9e29c444d.png
         * creationTime : 2020-04-15T15:13:59.943
         * id : 2
         */

        private int jobId;
        private int shopId;
        private int profitLossId;
        private double totalProfitLoss;
        private double splitRatio;
        private int actualWashScore;
        private double businessProfitLoss;
        private double platformProfitLoss;
        private int shopWashScore;
        private double washScoreProfit;
        private double shopDeductionMoney;
        private double shopPayMoney;
        private double shopArrears;
        private double platformIncome;
        private boolean isSettled;
        private String sign;
        private String creationTime;
        private int id;

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

        public int getProfitLossId() {
            return profitLossId;
        }

        public void setProfitLossId(int profitLossId) {
            this.profitLossId = profitLossId;
        }

        public double getTotalProfitLoss() {
            return totalProfitLoss;
        }

        public void setTotalProfitLoss(double totalProfitLoss) {
            this.totalProfitLoss = totalProfitLoss;
        }

        public double getSplitRatio() {
            return splitRatio;
        }

        public void setSplitRatio(double splitRatio) {
            this.splitRatio = splitRatio;
        }

        public int getActualWashScore() {
            return actualWashScore;
        }

        public void setActualWashScore(int actualWashScore) {
            this.actualWashScore = actualWashScore;
        }

        public double getBusinessProfitLoss() {
            return businessProfitLoss;
        }

        public void setBusinessProfitLoss(double businessProfitLoss) {
            this.businessProfitLoss = businessProfitLoss;
        }

        public double getPlatformProfitLoss() {
            return platformProfitLoss;
        }

        public void setPlatformProfitLoss(double platformProfitLoss) {
            this.platformProfitLoss = platformProfitLoss;
        }

        public int getShopWashScore() {
            return shopWashScore;
        }

        public void setShopWashScore(int shopWashScore) {
            this.shopWashScore = shopWashScore;
        }

        public double getWashScoreProfit() {
            return washScoreProfit;
        }

        public void setWashScoreProfit(double washScoreProfit) {
            this.washScoreProfit = washScoreProfit;
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

        public double getShopArrears() {
            return shopArrears;
        }

        public void setShopArrears(double shopArrears) {
            this.shopArrears = shopArrears;
        }

        public double getPlatformIncome() {
            return platformIncome;
        }

        public void setPlatformIncome(double platformIncome) {
            this.platformIncome = platformIncome;
        }

        public boolean isIsSettled() {
            return isSettled;
        }

        public void setIsSettled(boolean isSettled) {
            this.isSettled = isSettled;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
