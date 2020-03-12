package com.example.rainbow.bean;

public class ShopWinlossResponse {


    /**
     * code : 0
     * msg : success
     * data : {"shopId":4,"shopName":null,"arrears":0,"totalProfitLoss":0,"splitRatio":0.4,"actualWashScore":0,"businessProfitLoss":0,"platformProfitLoss":0,"creationTime":"2020-03-09T16:47:01.8983795+08:00","id":3}
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
         * shopId : 4
         * shopName : null
         * arrears : 0.0
         * totalProfitLoss : 0.0
         * splitRatio : 0.4
         * actualWashScore : 0
         * businessProfitLoss : 0.0
         * platformProfitLoss : 0.0
         * creationTime : 2020-03-09T16:47:01.8983795+08:00
         * id : 3
         */

        private int shopId;
        private Object shopName;
        private double arrears;
        private double totalProfitLoss;
        private double splitRatio;
        private int actualWashScore;
        private double businessProfitLoss;
        private double platformProfitLoss;
        private String creationTime;
        private int id;

        public int getShopId() {
            return shopId;
        }

        public void setShopId(int shopId) {
            this.shopId = shopId;
        }

        public Object getShopName() {
            return shopName;
        }

        public void setShopName(Object shopName) {
            this.shopName = shopName;
        }

        public double getArrears() {
            return arrears;
        }

        public void setArrears(double arrears) {
            this.arrears = arrears;
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
