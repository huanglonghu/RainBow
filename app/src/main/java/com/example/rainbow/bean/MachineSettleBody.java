package com.example.rainbow.bean;

public class MachineSettleBody {


    /**
     * jobId : 0
     * machineId : 0
     * numberImage : string
     * codeImage : string
     * totalBet : 0
     * totalOut : 0
     * totalWashScore : 0
     */

    private int jobId;
    private int machineId;
    private String numberImage;
    private String codeImage;
    private int totalBet;
    private int totalOut;
    private int totalWashScore;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public String getNumberImage() {
        return numberImage;
    }

    public void setNumberImage(String numberImage) {
        this.numberImage = numberImage;
    }

    public String getCodeImage() {
        return codeImage;
    }

    public void setCodeImage(String codeImage) {
        this.codeImage = codeImage;
    }

    public int getTotalBet() {
        return totalBet;
    }

    public void setTotalBet(int totalBet) {
        this.totalBet = totalBet;
    }

    public int getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(int totalOut) {
        this.totalOut = totalOut;
    }

    public int getTotalWashScore() {
        return totalWashScore;
    }

    public void setTotalWashScore(int totalWashScore) {
        this.totalWashScore = totalWashScore;
    }
}


