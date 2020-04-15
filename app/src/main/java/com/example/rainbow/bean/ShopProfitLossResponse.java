package com.example.rainbow.bean;

import java.util.List;

public class ShopProfitLossResponse {


    /**
     * code : 0
     * msg : success
     * data : {"splitRatio":0.4,"remarks":null,"machineProfitLoss":[{"isSettled":true,"profitLoss":700,"machineName":"D001三国演义","sort":1,"isFault":false,"id":1},{"isSettled":true,"profitLoss":1300,"machineName":"D002拳王争霸","sort":2,"isFault":false,"id":2},{"isSettled":true,"profitLoss":-500,"machineName":"D003老虎机","sort":3,"isFault":false,"id":3}],"shopName":"东圃店","contact":"老王","telephone":"18502085959","image":"images\\ef379f9383044c3f9c8f63399079790e.jpg","address":"广州天河东圃镇BRT","coordinate":"23.129776,113.408727","sort":0,"isSignIn":true,"isSettled":true,"isNotGo":false,"id":1}
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
         * splitRatio : 0.4
         * remarks : null
         * machineProfitLoss : [{"isSettled":true,"profitLoss":700,"machineName":"D001三国演义","sort":1,"isFault":false,"id":1},{"isSettled":true,"profitLoss":1300,"machineName":"D002拳王争霸","sort":2,"isFault":false,"id":2},{"isSettled":true,"profitLoss":-500,"machineName":"D003老虎机","sort":3,"isFault":false,"id":3}]
         * shopName : 东圃店
         * contact : 老王
         * telephone : 18502085959
         * image : images\ef379f9383044c3f9c8f63399079790e.jpg
         * address : 广州天河东圃镇BRT
         * coordinate : 23.129776,113.408727
         * sort : 0
         * isSignIn : true
         * isSettled : true
         * isNotGo : false
         * id : 1
         */

        private double splitRatio;
        private double deposit;
        private String remarks;
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
        private List<MachineProfitLossBean> machineProfitLoss;

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public double getSplitRatio() {
            return splitRatio;
        }

        public void setSplitRatio(double splitRatio) {
            this.splitRatio = splitRatio;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

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

        public List<MachineProfitLossBean> getMachineProfitLoss() {
            return machineProfitLoss;
        }

        public void setMachineProfitLoss(List<MachineProfitLossBean> machineProfitLoss) {
            this.machineProfitLoss = machineProfitLoss;
        }

        public static class MachineProfitLossBean {
            /**
             * isSettled : true
             * profitLoss : 700.0
             * machineName : D001三国演义
             * sort : 1
             * isFault : false
             * id : 1
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
    }
}
