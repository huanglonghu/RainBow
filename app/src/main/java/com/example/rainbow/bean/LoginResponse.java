package com.example.rainbow.bean;

public class LoginResponse {


    /**
     * code : 0
     * msg : success
     * data : {"userName":"丽丽","avatar":null,"userType":2,"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzaWQiOiIxIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IuS4veS4vSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6Ik1hY2hpbmVSb29tVXNlciIsImp0aSI6ImZkNzNmNzJkLWYwYWMtNDcwOS1iYjQ2LWQ0ZmU2MTA5ZjkzYyIsImlhdCI6MTU4MjQyMzUxNywibmJmIjoxNTgyNDIzNTE3LCJleHAiOjE1ODc2MDc1MTcsImlzcyI6IlNob3AiLCJhdWQiOiJ3ciJ9.Z0qK8cv3G0c6mvkqFQXcK5icGn7nx-rOdtOVT9Er_Ik","expired":0,"id":1}
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
         * userName : 丽丽
         * avatar : null
         * userType : 2
         * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzaWQiOiIxIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6IuS4veS4vSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6Ik1hY2hpbmVSb29tVXNlciIsImp0aSI6ImZkNzNmNzJkLWYwYWMtNDcwOS1iYjQ2LWQ0ZmU2MTA5ZjkzYyIsImlhdCI6MTU4MjQyMzUxNywibmJmIjoxNTgyNDIzNTE3LCJleHAiOjE1ODc2MDc1MTcsImlzcyI6IlNob3AiLCJhdWQiOiJ3ciJ9.Z0qK8cv3G0c6mvkqFQXcK5icGn7nx-rOdtOVT9Er_Ik
         * expired : 0
         * id : 1
         */

        private String userName;
        private Object avatar;
        private int userType;
        private String token;
        private int expired;
        private int id;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Object getAvatar() {
            return avatar;
        }

        public void setAvatar(Object avatar) {
            this.avatar = avatar;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getExpired() {
            return expired;
        }

        public void setExpired(int expired) {
            this.expired = expired;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
