package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.List;

public class XFRecord {


    /**
     * code : 0
     * msg : success
     * data : {"totalCount":1,"items":[{"machineId":1,"machineNumber":"1","machineName":"机器1","lossMoney":100,"userId":1,"entryPerson":"丽丽","creationTime":"2020-03-09T16:15:01.85","id":1}]}
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
         * totalCount : 1
         * items : [{"machineId":1,"machineNumber":"1","machineName":"机器1","lossMoney":100,"userId":1,"entryPerson":"丽丽","creationTime":"2020-03-09T16:15:01.85","id":1}]
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
             * machineId : 1
             * machineNumber : 1
             * machineName : 机器1
             * lossMoney : 100.0
             * userId : 1
             * entryPerson : 丽丽
             * creationTime : 2020-03-09T16:15:01.85
             * id : 1
             */

            private int machineId;
            private String machineNumber;
            private String machineName;
            private double lossMoney;
            private int userId;
            private String entryPerson;
            private String creationTime;
            private int id;

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

            public double getLossMoney() {
                return lossMoney;
            }

            public void setLossMoney(double lossMoney) {
                this.lossMoney = lossMoney;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getEntryPerson() {
                return entryPerson;
            }

            public void setEntryPerson(String entryPerson) {
                this.entryPerson = entryPerson;
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
