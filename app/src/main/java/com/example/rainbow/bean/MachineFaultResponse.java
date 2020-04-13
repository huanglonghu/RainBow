package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.List;

public class MachineFaultResponse {


    /**
     * code : 0
     * msg : string
     * data : {"totalCount":0,"items":[{"shopName":"string","machineId":0,"machineNumber":"string","machineName":"string","faultImage":"string","faultDescribe":"string","creationTime":"2020-04-13T03:03:34.466Z","handleImage":"string","handlePeople":"string","handleDate":"2020-04-13T03:03:34.466Z","handleDescribe":"string","faultState":0,"id":0}]}
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
         * totalCount : 0
         * items : [{"shopName":"string","machineId":0,"machineNumber":"string","machineName":"string","faultImage":"string","faultDescribe":"string","creationTime":"2020-04-13T03:03:34.466Z","handleImage":"string","handlePeople":"string","handleDate":"2020-04-13T03:03:34.466Z","handleDescribe":"string","faultState":0,"id":0}]
         */

        private int totalCount;
        private List<ItemsBean> items;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * shopName : string
             * machineId : 0
             * machineNumber : string
             * machineName : string
             * faultImage : string
             * faultDescribe : string
             * creationTime : 2020-04-13T03:03:34.466Z
             * handleImage : string
             * handlePeople : string
             * handleDate : 2020-04-13T03:03:34.466Z
             * handleDescribe : string
             * faultState : 0
             * id : 0
             */

            private String shopName;
            private int machineId;
            private String machineNumber;
            private String machineName;
            private String faultImage;
            private String faultDescribe;
            private String creationTime;
            private String handleImage;
            private String handlePeople;
            private String handleDate;
            private String handleDescribe;
            private int faultState;
            private int id;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public int getMachineId() {
                return machineId;
            }

            public void setMachineId(int machineId) {
                this.machineId = machineId;
            }

            public String getMachineNumber() {
                return machineNumber;
            }

            public void setMachineNumber(String machineNumber) {
                this.machineNumber = machineNumber;
            }

            public String getMachineName() {
                return machineName;
            }

            public void setMachineName(String machineName) {
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

            public String getCreationTime() {
                if(!TextUtils.isEmpty(creationTime)){
                    creationTime= TimeUtil.getStringToDate3(creationTime);
                }
                return creationTime;
            }

            public void setCreationTime(String creationTime) {
                this.creationTime = creationTime;
            }

            public String getHandleImage() {
                return handleImage;
            }

            public void setHandleImage(String handleImage) {
                this.handleImage = handleImage;
            }

            public String getHandlePeople() {
                return handlePeople;
            }

            public void setHandlePeople(String handlePeople) {
                this.handlePeople = handlePeople;
            }

            public String getHandleDate() {
                return handleDate;
            }

            public void setHandleDate(String handleDate) {
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
}
