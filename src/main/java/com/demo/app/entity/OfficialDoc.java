package com.demo.app.entity;

import java.io.Serializable;
import java.util.Date;

public class OfficialDoc implements Serializable {

    private static final long serialVersionUID = 4758947620528026982L;

    private String id;
    private String type;
    private String zh;
    private String title;
    private String unit1;
    private String unit2;
    private String content;
    private String accessory;
    private String drafter;
    private String dpt;
    private int status;
    private Date signdate;
    private Date crDate;
    private Date upDate;
    private String remark;
    private String name;
    private String flowname;
    private String flownameId;
    private String memo;
    private String comeUnit;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnit1() {
        return unit1;
    }

    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getDrafter() {
        return drafter;
    }

    public void setDrafter(String drafter) {
        this.drafter = drafter;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Date getCrDate() {
        return crDate;
    }

    public void setCrDate(Date crDate) {
        this.crDate = crDate;
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlowname() {
        return flowname;
    }

    public void setFlowname(String flowname) {
        this.flowname = flowname;
    }

    public String getFlownameId() {
        return flownameId;
    }

    public void setFlownameId(String flownameId) {
        this.flownameId = flownameId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getComeUnit() {
        return comeUnit;
    }

    public void setComeUnit(String comeUnit) {
        this.comeUnit = comeUnit;
    }

    @Override
    public String toString() {
        return "OfficialDoc{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", zh='" + zh + '\'' +
                ", title='" + title + '\'' +
                ", unit1='" + unit1 + '\'' +
                ", unit2='" + unit2 + '\'' +
                ", content='" + content + '\'' +
                ", accessory='" + accessory + '\'' +
                ", drafter='" + drafter + '\'' +
                ", dpt='" + dpt + '\'' +
                ", status=" + status +
                ", signdate=" + signdate +
                ", crDate=" + crDate +
                ", upDate=" + upDate +
                ", remark='" + remark + '\'' +
                ", name='" + name + '\'' +
                ", flowname='" + flowname + '\'' +
                ", flownameId='" + flownameId + '\'' +
                ", memo='" + memo + '\'' +
                ", comeUnit='" + comeUnit + '\'' +
                '}';
    }
}
