package com.example.rainbow.bean;

public class MachineFaultDetailResponse {


    /**
     * code : 0
     * msg : success
     * data : {"machineId":8,"machineNumber":null,"machineName":null,"faultImage":"images\\c4542753fab14ab29bcae2df336e0caa.jpg,images\\56fb4eaf785a4ab7bf57dff8a2e7ba97.jpg,images\\7832e0282f44435598c0283b105ce275.jpg,images\\291455b5fe8e42f688a274ec46fd0068.jpg","faultDescribe":"很多问题要处理\n","handleImage":null,"handlePeople":null,"handleDate":null,"handleDescribe":null,"faultState":0,"id":4}
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
         * machineNumber : null
         * machineName : null
         * faultImage : images\c4542753fab14ab29bcae2df336e0caa.jpg,images\56fb4eaf785a4ab7bf57dff8a2e7ba97.jpg,images\7832e0282f44435598c0283b105ce275.jpg,images\291455b5fe8e42f688a274ec46fd0068.jpg
         * faultDescribe : 很多问题要处理
         * handleImage : null
         * handlePeople : null
         * handleDate : null
         * handleDescribe : null
         * faultState : 0
         * id : 4
         */

        private int machineId;
        private Object machineNumber;
        private Object machineName;
        private String faultImage;
        private String faultDescribe;
        private String handleImage;
        private Object handlePeople;
        private Object handleDate;
        private String handleDescribe;
        private int faultState;
        private int id;

        public int getMachineId() {
            return machineId;
        }

        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }

        public Object getMachineNumber() {
            return machineNumber;
        }

        public void setMachineNumber(Object machineNumber) {
            this.machineNumber = machineNumber;
        }

        public Object getMachineName() {
            return machineName;
        }

        public void setMachineName(Object machineName) {
            this.machineName = machineName;
        }

        public String getFaultImage() {
            return faultImage;
        }

        public void setFaultImage(String faultImage) {
            this.faultImage = faultImage;
        }

        public String getFaultDescribe() {
            return faultDescribe;
        }

        public void setFaultDescribe(String faultDescribe) {
            this.faultDescribe = faultDescribe;
        }

        public String getHandleImage() {
            return handleImage;
        }

        public void setHandleImage(String handleImage) {
            this.handleImage = handleImage;
        }

        public Object getHandlePeople() {
            return handlePeople;
        }

        public void setHandlePeople(Object handlePeople) {
            this.handlePeople = handlePeople;
        }

        public Object getHandleDate() {
            return handleDate;
        }

        public void setHandleDate(Object handleDate) {
            this.handleDate = handleDate;
        }

        public String getHandleDescribe() {
            return handleDescribe;
        }

        public void setHandleDescribe(String handleDescribe) {
            this.handleDescribe = handleDescribe;
        }

        public int getFaultState() {
            return faultState;
        }

        public void setFaultState(int faultState) {
            this.faultState = faultState;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
