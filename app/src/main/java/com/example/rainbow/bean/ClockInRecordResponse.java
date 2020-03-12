package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.List;

public class ClockInRecordResponse {


    /**
     * code : 0
     * msg : success
     * data : {"totalCount":1,"items":[{"userId":1,"userName":"aa","date":"2020-02-27","startWorkTime":"2020-02-27","endWorkTime":"2020-02-27","state":0,"id":1}]}
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
         * items : [{"userId":1,"userName":"aa","date":"2020-02-27","startWorkTime":"2020-02-27","endWorkTime":"2020-02-27","state":0,"id":1}]
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
             * userId : 1
             * userName : aa
             * date : 2020-02-27
             * startWorkTime : 2020-02-27
             * endWorkTime : 2020-02-27
             * state : 0
             * id : 1
             */

            private int userId;
            private String userName;
            private String date;
            private String startWorkTime;
            private String endWorkTime;
            private int state;
            private int id;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getDate() {
                if(!TextUtils.isEmpty(date)){
                    date= TimeUtil.getStringToDate(date);
                }
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getStartWorkTime() {
                if(!TextUtils.isEmpty(startWorkTime)){
                    startWorkTime= TimeUtil.getStringToDate3(startWorkTime);
                }
                return startWorkTime;
            }

            public void setStartWorkTime(String startWorkTime) {
                this.startWorkTime = startWorkTime;
            }

            public String getEndWorkTime() {
                if(!TextUtils.isEmpty(endWorkTime)){
                    endWorkTime= TimeUtil.getStringToDate3(endWorkTime);
                }
                return endWorkTime;
            }

            public void setEndWorkTime(String endWorkTime) {
                this.endWorkTime = endWorkTime;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
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
