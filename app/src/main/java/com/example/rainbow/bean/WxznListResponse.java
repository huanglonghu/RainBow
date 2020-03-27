package com.example.rainbow.bean;

import java.util.List;

public class WxznListResponse {
    /**
     * code : 0
     * msg : success
     * data : {"totalCount":1,"items":[{"title":"除了继续用\u201c武汉病毒\u201d，蓬佩奥还威胁对中国秋后算账","creationTime":"2020-03-27T09:34:21.823","id":1}]}
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
         * items : [{"title":"除了继续用\u201c武汉病毒\u201d，蓬佩奥还威胁对中国秋后算账","creationTime":"2020-03-27T09:34:21.823","id":1}]
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
             * title : 除了继续用“武汉病毒”，蓬佩奥还威胁对中国秋后算账
             * creationTime : 2020-03-27T09:34:21.823
             * id : 1
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
