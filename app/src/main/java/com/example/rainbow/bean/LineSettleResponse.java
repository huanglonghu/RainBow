package com.example.rainbow.bean;

public class LineSettleResponse {
    /**
     * code : 0
     * msg : success
     * data : {"totalProfitLoss":3750,"loanMoney":-1450,"deductionMoney":0,"washScoreMoney":270,"platformIncome":4020}
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
         * totalProfitLoss : 3750.0
         * loanMoney : -1450.0
         * deductionMoney : 0.0
         * washScoreMoney : 270.0
         * platformIncome : 4020.0
         */

        private double totalProfitLoss;
        private double loanMoney;
        private double deductionMoney;
        private double washScoreMoney;
        private double platformIncome;

        public double getTotalProfitLoss() {
            return totalProfitLoss;
        }

        public void setTotalProfitLoss(double totalProfitLoss) {
            this.totalProfitLoss = totalProfitLoss;
        }

        public double getLoanMoney() {
            return loanMoney;
        }

        public void setLoanMoney(double loanMoney) {
            this.loanMoney = loanMoney;
        }

        public double getDeductionMoney() {
            return deductionMoney;
        }

        public void setDeductionMoney(double deductionMoney) {
            this.deductionMoney = deductionMoney;
        }

        public double getWashScoreMoney() {
            return washScoreMoney;
        }

        public void setWashScoreMoney(double washScoreMoney) {
            this.washScoreMoney = washScoreMoney;
        }

        public double getPlatformIncome() {
            return platformIncome;
        }

        public void setPlatformIncome(double platformIncome) {
            this.platformIncome = platformIncome;
        }
    }
}
