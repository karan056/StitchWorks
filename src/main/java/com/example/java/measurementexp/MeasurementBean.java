package com.example.java.measurementexp;

public class MeasurementBean {
    String orderId;
    String mobile;
    String dress;
    String dod;
    String bill;
    String worker;
    String workstatus;

    public MeasurementBean(String orderId, String mobile, String dress, String dod, String bill, String worker, String workstatus) {
        this.orderId = orderId;
        this.mobile = mobile;
        this.dress = dress;
        this.dod = dod;
        this.bill = bill;
        this.worker = worker;
        this.workstatus = workstatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    public String getDod() {
        return dod;
    }

    public void setDod(String dod) {
        this.dod = dod;
    }

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getWorkstatus() {
        return workstatus;
    }

    public void setWorkstatus(String workstatus) {
        this.workstatus = workstatus;
    }
}
