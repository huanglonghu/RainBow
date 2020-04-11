package com.example.rainbow.bean;

import java.util.List;

public class ShopProfitLossResponse {


    /**
     * code : 0
     * msg : success
     * data : {"splitRatio":0.3,"remarks":null,"machineProfitLoss":[{"isSettled":false,"profitLoss":0,"machineName":"赛车","sort":1,"isFault":false,"id":17},{"isSettled":false,"profitLoss":0,"machineName":"水果乐园","sort":2,"isFault":true,"id":18},{"isSettled":false,"profitLoss":0,"machineName":"消消乐","sort":3,"isFault":true,"id":19}],"shopName":"棠下店","contact":"李老板","telephone":"18502085956","image":"images\\bda44826719d4ac69dc9bf8564d61545.jpg","address":"天河区棠下小区三愧里大街5巷1栋","coordinate":"23.136651,113.382284","sort":0,"isSignIn":false,"isSettled":false,"isNotGo":false,"id":8}
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
         * splitRatio : 0.3
         * remarks : null
         * machineProfitLoss : [{"isSettled":false,"profitLoss":0,"machineName":"赛车","sort":1,"isFault":false,"id":17},{"isSettled":false,"profitLoss":0,"machineName":"水果乐园","sort":2,"isFault":true,"id":18},{"isSettled":false,"profitLoss":0,"machineName":"消消乐","sort":3,"isFault":true,"id":19}]
         * shopName : 棠下店
         * contact : 李老板
         * telephone : 18502085956
         * image : images\bda44826719d4ac69dc9bf8564d61545.jpg
         * address : 天河区棠下小区三愧里大街5巷1栋
         * coordinate : 23.136651,113.382284
         * sort : 0
         * isSignIn : false
         * isSettled : false
         * isNotGo : false
         * id : 8
         */

        private double splitRatio;
        private Object remarks;
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
             * isSettled : false
             * profitLoss : 0.0
             * machineName : 赛车
             * sort : 1
             * isFault : false
             * id : 17
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
