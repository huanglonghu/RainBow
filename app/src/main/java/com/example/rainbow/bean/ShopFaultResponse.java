package com.example.rainbow.bean;

import java.util.List;

public class ShopFaultResponse {
    /**
     * code : 0
     * msg : success
     * data : {"shopName":"棠下店","contact":"李老板","telephone":"18502085956","image":"images\\bda44826719d4ac69dc9bf8564d61545.jpg","address":"天河区棠下小区三愧里大街5巷1栋","coordinate":"23.136651,113.382284","isSignIn":false,"isSettled":false,"splitRatio":0.3,"remarks":null,"machineFault":[{"machineName":"水果乐园","sort":2,"isFault":true,"id":18},{"machineName":"消消乐","sort":3,"isFault":true,"id":19},{"machineName":"赛车","sort":1,"isFault":false,"id":17}],"id":8}
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
         * shopName : 棠下店
         * contact : 李老板
         * telephone : 18502085956
         * image : images\bda44826719d4ac69dc9bf8564d61545.jpg
         * address : 天河区棠下小区三愧里大街5巷1栋
         * coordinate : 23.136651,113.382284
         * isSignIn : false
         * isSettled : false
         * splitRatio : 0.3
         * remarks : null
         * machineFault : [{"machineName":"水果乐园","sort":2,"isFault":true,"id":18},{"machineName":"消消乐","sort":3,"isFault":true,"id":19},{"machineName":"赛车","sort":1,"isFault":false,"id":17}]
         * id : 8
         */

        private String shopName;
        private String contact;
        private String telephone;
        private String image;
        private String address;
        private String coordinate;
        private boolean isSignIn;
        private boolean isSettled;
        private double splitRatio;
        private Object remarks;
        private int id;
        private List<MachineFaultBean> machineFault;

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

        public double getSplitRatio() {
            return splitRatio;
        }

        public void setSplitRatio(double splitRatio) {
            this.splitRatio = splitRatio;
        }

        public Object getRemarks() {
            return remarks;
        }

        public void setRemarks(Object remarks) {
            this.remarks = remarks;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<MachineFaultBean> getMachineFault() {
            return machineFault;
        }

        public void setMachineFault(List<MachineFaultBean> machineFault) {
            this.machineFault = machineFault;
        }

        public static class MachineFaultBean {
            /**
             * machineName : 水果乐园
             * sort : 2
             * isFault : true
             * id : 18
             */

            private String machineName;
            private int sort;
            private boolean isFault;
            private int id;

            public String getMachineName() {
                return machineName;
            }

            public void setMachineName(String machineName) {
                this.machineName = machineName;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public boolean isIsFault() {
                return isFault;
            }

            public void setIsFault(boolean isFault) {
                this.isFault = isFault;
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
