package com.example.rainbow.bean;

import java.util.List;

public class GetJobDetailResponse {


    /**
     * code : 0
     * msg : success
     * data : {"routeId":1,"userName":"cc","driver":"司机1号","carNumber":"456","missionType":1,"isSettled":false,"spareParts":[],"shops":[{"shopName":"D店铺","contact":"小d","telephone":"123","image":"string","address":"广州天河","sort":1,"isSignIn":true,"isSettled":true,"isNotGo":false,"id":4},{"shopName":"A店铺","contact":"小a","telephone":"123","image":"string","address":"广州天河","sort":2,"isSignIn":true,"isSettled":false,"isNotGo":true,"id":1},{"shopName":"E店铺","contact":"小e","telephone":"123","image":"string","address":"广州天河","sort":3,"isSignIn":true,"isSettled":false,"isNotGo":false,"id":5}],"routeName":"华师到番禺1","id":1}
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
         * routeId : 1
         * userName : cc
         * driver : 司机1号
         * carNumber : 456
         * missionType : 1
         * isSettled : false
         * spareParts : []
         * shops : [{"shopName":"D店铺","contact":"小d","telephone":"123","image":"string","address":"广州天河","sort":1,"isSignIn":true,"isSettled":true,"isNotGo":false,"id":4},{"shopName":"A店铺","contact":"小a","telephone":"123","image":"string","address":"广州天河","sort":2,"isSignIn":true,"isSettled":false,"isNotGo":true,"id":1},{"shopName":"E店铺","contact":"小e","telephone":"123","image":"string","address":"广州天河","sort":3,"isSignIn":true,"isSettled":false,"isNotGo":false,"id":5}]
         * routeName : 华师到番禺1
         * id : 1
         */

        private int routeId;
        private String userName;
        private String driver;
        private String carNumber;
        private int missionType;
        private boolean isSettled;
        private String routeName;
        private int id;
        private List<?> spareParts;
        private List<ShopsBean> shops;

        public int getRouteId() {
            return routeId;
        }

        public void setRouteId(int routeId) {
            this.routeId = routeId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public int getMissionType() {
            return missionType;
        }

        public void setMissionType(int missionType) {
            this.missionType = missionType;
        }

        public boolean isIsSettled() {
            return isSettled;
        }

        public void setIsSettled(boolean isSettled) {
            this.isSettled = isSettled;
        }

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

        public List<?> getSpareParts() {
            return spareParts;
        }

        public void setSpareParts(List<?> spareParts) {
            this.spareParts = spareParts;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class ShopsBean {
            /**
             * shopName : D店铺
             * contact : 小d
             * telephone : 123
             * image : string
             * address : 广州天河
             * sort : 1
             * isSignIn : true
             * isSettled : true
             * isNotGo : false
             * id : 4
             */

            private String shopName;
            private String contact;
            private String telephone;
            private String image;
            private String address;
            private int sort;
            private boolean isSignIn;
            private boolean isSettled;
            private boolean isNotGo;
            private int id;

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public boolean isIsSignIn() {
                return isSignIn;
            }

            public void setIsSignIn(boolean isSignIn) {
                this.isSignIn = isSignIn;
            }

            public boolean isIsSettled() {
                return isSettled;
            }

            public void setIsSettled(boolean isSettled) {
                this.isSettled = isSettled;
            }

            public boolean isIsNotGo() {
                return isNotGo;
            }

            public void setIsNotGo(boolean isNotGo) {
                this.isNotGo = isNotGo;
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
