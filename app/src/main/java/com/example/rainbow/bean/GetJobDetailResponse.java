package com.example.rainbow.bean;

import java.util.List;

public class GetJobDetailResponse {


    /**
     * code : 0
     * msg : success
     * data : {"routeId":1,"userName":"steven","driver":"老司机","carNumber":"粤A66666","missionType":1,"isSettled":false,"spareParts":[],"shops":[{"shopName":"东圃店","contact":"老王","telephone":"18502085959","image":"images\\ef379f9383044c3f9c8f63399079790e.jpg","address":"广州天河东圃镇BRT","coordinate":"23.129776,113.408727","sort":1,"isSignIn":true,"isSettled":true,"isNotGo":false,"id":1},{"shopName":"棠下店","contact":"老张","telephone":"18666666666","image":"images\\3237a7ba3c98433c933d837826de76b0.jpg","address":"广州市天河区棠下BRT","coordinate":"23.131874,113.386972","sort":2,"isSignIn":true,"isSettled":false,"isNotGo":true,"id":3},{"shopName":"车陂店","contact":"老李","telephone":"18555555555","image":"images\\e0d798dc6e0d4b49a48b8bf3eceeb6dd.jpg","address":"广州市天河区车陂街道","coordinate":"23.130736,113.401971","sort":3,"isSignIn":true,"isSettled":false,"isNotGo":true,"id":2}],"routeName":"东圃---棠下","startTime":"2020-04-11T00:00:00","id":1}
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
         * userName : steven
         * driver : 老司机
         * carNumber : 粤A66666
         * missionType : 1
         * isSettled : false
         * spareParts : []
         * shops : [{"shopName":"东圃店","contact":"老王","telephone":"18502085959","image":"images\\ef379f9383044c3f9c8f63399079790e.jpg","address":"广州天河东圃镇BRT","coordinate":"23.129776,113.408727","sort":1,"isSignIn":true,"isSettled":true,"isNotGo":false,"id":1},{"shopName":"棠下店","contact":"老张","telephone":"18666666666","image":"images\\3237a7ba3c98433c933d837826de76b0.jpg","address":"广州市天河区棠下BRT","coordinate":"23.131874,113.386972","sort":2,"isSignIn":true,"isSettled":false,"isNotGo":true,"id":3},{"shopName":"车陂店","contact":"老李","telephone":"18555555555","image":"images\\e0d798dc6e0d4b49a48b8bf3eceeb6dd.jpg","address":"广州市天河区车陂街道","coordinate":"23.130736,113.401971","sort":3,"isSignIn":true,"isSettled":false,"isNotGo":true,"id":2}]
         * routeName : 东圃---棠下
         * startTime : 2020-04-11T00:00:00
         * id : 1
         */

        private int routeId;
        private String userName;
        private String driver;
        private String carNumber;
        private int missionType;
        private boolean isSettled;
        private String routeName;
        private String startTime;
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

        public String getStartTime() {
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
             * shopName : 东圃店
             * contact : 老王
             * telephone : 18502085959
             * image : images\ef379f9383044c3f9c8f63399079790e.jpg
             * address : 广州天河东圃镇BRT
             * coordinate : 23.129776,113.408727
             * sort : 1
             * isSignIn : true
             * isSettled : true
             * isNotGo : false
             * id : 1
             */

            private String shopName;
            private String contact;
            private String telephone;
            private String image;
            private String address;
            private String coordinate;
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

            public String getCoordinate() {
                return coordinate;
            }

            public void setCoordinate(String coordinate) {
                this.coordinate = coordinate;
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
