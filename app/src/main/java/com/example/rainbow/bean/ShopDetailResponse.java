package com.example.rainbow.bean;

import java.util.List;

public class ShopDetailResponse {


    /**
     * code : 0
     * msg : success
     * data : {"shopName":"D店铺","contact":"小d","telephone":"123","image":"string","address":"广州天河","coordinate":null,"isSignIn":true,"isSettled":false,"splitRatio":0.4,"remarks":null,"machineProfitLoss":[{"isSettled":true,"profitLoss":0,"machineName":"机器8","sort":1,"isFault":true,"id":8}],"machineFault":[{"machineName":"机器8","sort":1,"isFault":true,"id":8}],"id":4}
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
         * shopName : D店铺
         * contact : 小d
         * telephone : 123
         * image : string
         * address : 广州天河
         * coordinate : null
         * isSignIn : true
         * isSettled : false
         * splitRatio : 0.4
         * remarks : null
         * machineProfitLoss : [{"isSettled":true,"profitLoss":0,"machineName":"机器8","sort":1,"isFault":true,"id":8}]
         * machineFault : [{"machineName":"机器8","sort":1,"isFault":true,"id":8}]
         * id : 4
         */

        private String shopName;
        private String contact;
        private String telephone;
        private String image;
        private String address;
        private Object coordinate;
        private boolean isSignIn;
        private boolean isSettled;
        private double splitRatio;
        private Object remarks;
        private int id;
        private List<MachineProfitLossBean> machineProfitLoss;
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

        public Object getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(Object coordinate) {
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

        public List<MachineProfitLossBean> getMachineProfitLoss() {
            return machineProfitLoss;
        }

        public void setMachineProfitLoss(List<MachineProfitLossBean> machineProfitLoss) {
            this.machineProfitLoss = machineProfitLoss;
        }

        public List<MachineFaultBean> getMachineFault() {
            return machineFault;
        }

        public void setMachineFault(List<MachineFaultBean> machineFault) {
            this.machineFault = machineFault;
        }

        public static class MachineProfitLossBean {
            /**
             * isSettled : true
             * profitLoss : 0.0
             * machineName : 机器8
             * sort : 1
             * isFault : true
             * id : 8
             */

            private boolean isSettled;
            private double profitLoss;
            private String machineName;
            private int sort;
            private boolean isFault;
            private int id;

            public boolean isIsSettled() {
                return isSettled;
            }

            public void setIsSettled(boolean isSettled) {
                this.isSettled = isSettled;
            }

            public double getProfitLoss() {
                return profitLoss;
            }

            public void setProfitLoss(double profitLoss) {
                this.profitLoss = profitLoss;
            }

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

        public static class MachineFaultBean {
            /**
             * machineName : 机器8
             * sort : 1
             * isFault : true
             * id : 8
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
