package com.example.rainbow.bean;

import android.text.TextUtils;

import com.example.rainbow.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class NoticeDetailResponse {
    /**
     * code : 0
     * msg : success
     * data : {"content":"","isPublish":true,"title":"除了继续用\u201c武汉病毒\u201d，蓬佩奥还威胁对中国秋后算账","creationTime":"2020-03-27T09:34:21.823","id":1}
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
         * content :
         * isPublish : true
         * title : 除了继续用“武汉病毒”，蓬佩奥还威胁对中国秋后算账
         * creationTime : 2020-03-27T09:34:21.823
         * id : 1
         */

        private String content;
        private boolean isPublish;
        private String title;
        private String creationTime;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean isIsPublish() {
            return isPublish;
        }

        public void setIsPublish(boolean isPublish) {
            this.isPublish = isPublish;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
