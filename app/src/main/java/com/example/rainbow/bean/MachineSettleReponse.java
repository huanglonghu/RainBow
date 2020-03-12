package com.example.rainbow.bean;

public class MachineSettleReponse {
    /**
     * code : 0
     * msg : success
     * data : {"machineId":8,"numberImage":null,"codeImage":null,"totalBet":0,"totalOut":0,"totalWashScore":0,"bet":0,"out":0,"washScore":0,"profitLoss":0,"isRepeat":false,"id":2}
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
         * machineId : 8
         * numberImage : null
         * codeImage : null
         * totalBet : 0
         * totalOut : 0
         * totalWashScore : 0
         * bet : 0
         * out : 0
         * washScore : 0
         * profitLoss : 0.0
         * isRepeat : false
         * id : 2
         */

        private int machineId;
        private Object numberImage;
        private Object codeImage;
        private int totalBet;
        private int totalOut;
        private int totalWashScore;
        private int bet;
        private int out;
        private int washScore;
        private double profitLoss;
        private boolean isRepeat;
        private int id;

        public int getMachineId() {
            return machineId;
        }

        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }

        public Object getNumberImage() {
            return numberImage;
        }

        public void setNumberImage(Object numberImage) {
            this.numberImage = numberImage;
        }

        public Object getCodeImage() {
            return codeImage;
        }

        public void setCodeImage(Object codeImage) {
            this.codeImage = codeImage;
        }

        public int getTotalBet() {
            return totalBet;
        }

        public void setTotalBet(int totalBet) {
            this.totalBet = totalBet;
        }

        public int getTotalOut() {
            return totalOut;
        }

        public void setTotalOut(int totalOut) {
            this.totalOut = totalOut;
        }

        public int getTotalWashScore() {
            return totalWashScore;
        }

        public void setTotalWashScore(int totalWashScore) {
            this.totalWashScore = totalWashScore;
        }

        public int getBet() {
            return bet;
        }

        public void setBet(int bet) {
            this.bet = bet;
        }

        public int getOut() {
            return out;
        }

        public void setOut(int out) {
            this.out = out;
        }

        public int getWashScore() {
            return washScore;
        }

        public void setWashScore(int washScore) {
            this.washScore = washScore;
        }

        public double getProfitLoss() {
            return profitLoss;
        }

        public void setProfitLoss(double profitLoss) {
            this.profitLoss = profitLoss;
        }

        public boolean isIsRepeat() {
            return isRepeat;
        }

        public void setIsRepeat(boolean isRepeat) {
            this.isRepeat = isRepeat;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
