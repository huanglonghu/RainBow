package com.example.rainbow.bean;

public class MachineGuideBody {


    /**
     * machineName : string
     * machineModel : string
     * title : string
     * page : 0
     * limit : 0
     */

    private String machineName;
    private String machineModel;
    private String title;
    private int page;
    private int limit;

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(String machineModel) {
        this.machineModel = machineModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
