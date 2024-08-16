package com.example.java.tableview;

public class WorkerBean
{
    String wname;
    String mobile;
    String spcl;

    public WorkerBean(String wname, String mobile, String spcl) {
        this.wname = wname;
        this.mobile = mobile;
        this.spcl = spcl;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSpcl() {
        return spcl;
    }

    public void setSpcl(String spcl) {
        this.spcl = spcl;
    }
}
