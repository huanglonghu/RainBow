package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.List;

public class NoticeListResponse {


    /**
     * code : 0
     * msg : string
     * data : {"totalCount":0,"items":[{"title":"string","creationTime":"2020-03-10T09:52:04.293Z","id":0}]}
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
         * items : [{"title":"string","creationTime":"2020-03-10T09:52:04.293Z","id":0}]
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
             * title : string
             * creationTime : 2020-03-10T09:52:04.293Z
             * id : 0
             */

            private String title;
            private String creationTime;
            private int id;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCreationTime() {
                if (!TextUtils.isEmpty(creationTime)) {
                    creationTime = TimeUtil.getStringToDate3(creationTime);
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
