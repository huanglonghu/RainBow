package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.List;

public class MachineDetailResponse {


    /**
     * code : 0
     * msg : success
     * data : {"isSettled":true,"numberImage":null,"codeImage":null,"totalBet":0,"totalOut":0,"totalWashScore":0,"bet":-1000,"out":-500,"washScore":-100,"profitLoss":-500,"machineHistoryProfitLoss":[{"numberImage":null,"codeImage":null,"totalBet":0,"totalOut":0,"totalWashScore":0,"creationTime":"2020-03-05T20:57:37.177","id":3},{"numberImage":"string","codeImage":"string","totalBet":1000,"totalOut":500,"totalWashScore":100,"creationTime":"2020-02-24T15:49:52.52","id":1}],"machineFaults":[{"faultDescribe":"没电啦","faultState":0,"creationTime":"2020-03-10T10:08:55.067","id":3}],"machineName":"机器1","id":1}
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
         * isSettled : true
         * numberImage : null
         * codeImage : null
         * totalBet : 0
         * totalOut : 0
         * totalWashScore : 0
         * bet : -1000
         * out : -500
         * washScore : -100
         * profitLoss : -500.0
         * machineHistoryProfitLoss : [{"numberImage":null,"codeImage":null,"totalBet":0,"totalOut":0,"totalWashScore":0,"creationTime":"2020-03-05T20:57:37.177","id":3},{"numberImage":"string","codeImage":"string","totalBet":1000,"totalOut":500,"totalWashScore":100,"creationTime":"2020-02-24T15:49:52.52","id":1}]
         * machineFaults : [{"faultDescribe":"没电啦","faultState":0,"creationTime":"2020-03-10T10:08:55.067","id":3}]
         * machineName : 机器1
         * id : 1
         */

        private boolean isSettled;
        private double principal;
        private Object numberImage;
        private Object codeImage;
        private int totalBet;
        private int totalOut;
        private int totalWashScore;
        private int bet;
        private int out;
        private int washScore;
        private double profitLoss;
        private String machineName;
        private int id;
        private List<MachineHistoryProfitLossBean> machineHistoryProfitLoss;
        private List<MachineFaultsBean> machineFaults;

        public double getPrincipal() {
            return principal;
        }

        public void setPrincipal(double principal) {
            this.principal = principal;
        }

        public boolean isIsSettled() {
            return isSettled;
        }

        public void setIsSettled(boolean isSettled) {
            this.isSettled = isSettled;
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

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<MachineHistoryProfitLossBean> getMachineHistoryProfitLoss() {
            return machineHistoryProfitLoss;
        }

        public void setMachineHistoryProfitLoss(List<MachineHistoryProfitLossBean> machineHistoryProfitLoss) {
            this.machineHistoryProfitLoss = machineHistoryProfitLoss;
        }

        public List<MachineFaultsBean> getMachineFaults() {
            return machineFaults;
        }

        public void setMachineFaults(List<MachineFaultsBean> machineFaults) {
            this.machineFaults = machineFaults;
        }

        public static class MachineHistoryProfitLossBean {
            /**
             * numberImage : null
             * codeImage : null
             * totalBet : 0
             * totalOut : 0
             * totalWashScore : 0
             * creationTime : 2020-03-05T20:57:37.177
             * id : 3
             */

            private String numberImage;
            private String codeImage;
            private int totalBet;
            private int totalOut;
            private int totalWashScore;
            private String creationTime;
            private int id;

            public String getNumberImage() {
                return numberImage;
            }

            public void setNumberImage(String numberImage) {
                this.numberImage = numberImage;
            }

            public String getCodeImage() {
                return codeImage;
            }

            public void setCodeImage(String codeImage) {
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

            public String getCreationTime() {
                if(!TextUtils.isEmpty(creationTime)){
                    creationTime= TimeUtil.getStringToDate3(creationTime);
                }
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

        public static class MachineFaultsBean {
            /**
             * faultDescribe : 没电啦
             * faultState : 0
             * creationTime : 2020-03-10T10:08:55.067
             * id : 3
             */

            private String faultDescribe;
            private int faultState;
            private String creationTime;
            private int id;

            public String getFaultDescribe() {
                return faultDescribe;
            }

            public void setFaultDescribe(String faultDescribe) {
                this.faultDescribe = faultDescribe;
            }

            public int getFaultState() {
                return faultState;
            }

            public void setFaultState(int faultState) {
                this.faultState = faultState;
            }

            public String getCreationTime() {
                if(!TextUtils.isEmpty(creationTime)){
                    creationTime= TimeUtil.getStringToDate3(creationTime);
                }
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
}
