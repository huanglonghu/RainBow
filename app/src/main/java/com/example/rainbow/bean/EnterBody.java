package com.example.rainbow.bean;

public class EnterBody {


    /**
     * machineId : 0
     * lossMoney : 0
     * userId : 0
     * entryPerson : string
     */

    private int machineId;
    private int lossMoney;
    private int userId;
    private String entryPerson;

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getLossMoney() {
        return lossMoney;
    }

    public void setLossMoney(int lossMoney) {
        this.lossMoney = lossMoney;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEntryPerson() {
        return entryPerson;
    }

    public void setEntryPerson(String entryPerson) {
        this.entryPerson = entryPerson;
    }
}
