package com.example.rainbow.bean;

import java.util.List;

public class GetJobsResponse {


    /**
     * code : 0
     * msg : success
     * data : {"items":[{"routeName":"华师到番禺1","id":1},{"routeName":"华师到番禺2","id":2}]}
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
             * id : 1
             */

            private String routeName;
            private int id;

            public String getRouteName() {
                return routeName;
            }

            public void setRouteName(String routeName) {
                this.routeName = routeName;
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
