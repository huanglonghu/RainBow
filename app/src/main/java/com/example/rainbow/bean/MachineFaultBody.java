package com.example.rainbow.bean;

public class MachineFaultBody {


    /**
     * shopId : 0
     * machineId : 0
     * machineNumber : string
     * machineName : string
     * faultState : 0
     * page : 0
     * limit : 0
     */

    private int shopId;
    private int machineId;
    private String machineNumber;
    private String machineName;
    private int page;
    private int limit;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
