package com.example.rainbow.bean;

public class HandlerFaultBody {


    /**
     * jobId : 0
     * handleImage : string
     * handlePeople : string
     * handleDescribe : string
     * faultState : 0
     * lastModifierUserId : 0
     * id : 0
     */

    private int jobId;
    private String handleImage;
    private String handlePeople;
    private String handleDescribe;
    private int faultState;
    private int lastModifierUserId;
    private int id;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getHandleImage() {
        return handleImage;
    }

    public void setHandleImage(String handleImage) {
        this.handleImage = handleImage;
    }

    public String getHandlePeople() {
        return handlePeople;
    }

    public void setHandlePeople(String handlePeople) {
        this.handlePeople = handlePeople;
    }

    public String getHandleDescribe() {
        return handleDescribe;
    }

    public void setHandleDescribe(String handleDescribe) {
        this.handleDescribe = handleDescribe;
    }

    public int getFaultState() {
        return faultState;
    }

    public void setFaultState(int faultState) {
        this.faultState = faultState;
    }

    public int getLastModifierUserId() {
        return lastModifierUserId;
    }

    public void setLastModifierUserId(int lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
