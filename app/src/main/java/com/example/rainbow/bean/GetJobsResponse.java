package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.List;

public class GetJobsResponse {


    /**
     * code : 0
     * msg : success
     * data : {"items":[{"routeName":"华师到番禺1","startTime":"2020-04-07T00:00:00","id":34},{"routeName":"华师到番禺2","startTime":"2020-04-07T00:00:00","id":35}]}
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
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * routeName : 华师到番禺1
             * startTime : 2020-04-07T00:00:00
             * id : 34
             */

            private String routeName;
            private String startTime;
            private int id;

            public String getRouteName() {
                return routeName;
            }

            public void setRouteName(String routeName) {
                this.routeName = routeName;
            }

            public String getStartTime() {
                if(!TextUtils.isEmpty(startTime)){
                    startTime= TimeUtil.getStringToDate(startTime);
                }
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
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
