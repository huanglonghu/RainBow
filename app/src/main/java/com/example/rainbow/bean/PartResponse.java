package com.example.rainbow.bean;

import java.util.List;

public class PartResponse {


    /**
     * code : 0
     * msg : success
     * data : {"items":[{"jobId":7,"sparePartName":"小零件01","number":4,"id":5},{"jobId":7,"sparePartName":"小零件01","number":2,"id":4}]}
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
             * jobId : 7
             * sparePartName : 小零件01
             * number : 4
             * id : 5
             */

            private int jobId;
            private String sparePartName;
            private int number;
            private int id;

            public int getJobId() {
                return jobId;
            }

            public void setJobId(int jobId) {
                this.jobId = jobId;
            }

            public String getSparePartName() {
                return sparePartName;
            }

            public void setSparePartName(String sparePartName) {
                this.sparePartName = sparePartName;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
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
