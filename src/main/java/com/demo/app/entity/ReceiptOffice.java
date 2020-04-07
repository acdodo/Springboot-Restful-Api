package com.demo.app.entity;

import java.io.Serializable;
import java.util.Date;

public class ReceiptOffice implements Serializable {

    private static final long serialVersionUID = -6938709913917417079L;

    private int id;
    private String title;
    private String identifier;
    private Date createdate;
    private String type;
    private String urgent;
    private String keyword;
    private String unit;
    private Date enddate;
    private String dutysubject;
    private String booker;
    private Date bookdate;
    private String flow;
    private String imitate;
    private String passview;
    private String daoview;
    private String passround;
    private String remark;
    private String status;
    private String account;
    private String content;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getDutysubject() {
        return dutysubject;
    }

    public void setDutysubject(String dutysubject) {
        this.dutysubject = dutysubject;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public Date getBookdate() {
        return bookdate;
    }

    public void setBookdate(Date bookdate) {
        this.bookdate = bookdate;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getImitate() {
        return imitate;
    }

    public void setImitate(String imitate) {
        this.imitate = imitate;
    }

    public String getPassview() {
        return passview;
    }

    public void setPassview(String passview) {
        this.passview = passview;
    }

    public String getDaoview() {
        return daoview;
    }

    public void setDaoview(String daoview) {
        this.daoview = daoview;
    }

    public String getPassround() {
        return passround;
    }

    public void setPassround(String passround) {
        this.passround = passround;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReceiptOffice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", identifier='" + identifier + '\'' +
                ", createdate=" + createdate +
                ", type='" + type + '\'' +
                ", urgent='" + urgent + '\'' +
                ", keyword='" + keyword + '\'' +
                ", unit='" + unit + '\'' +
                ", enddate=" + enddate +
                ", dutysubject='" + dutysubject + '\'' +
                ", booker='" + booker + '\'' +
                ", bookdate=" + bookdate +
                ", flow='" + flow + '\'' +
                ", imitate='" + imitate + '\'' +
                ", passview='" + passview + '\'' +
                ", daoview='" + daoview + '\'' +
                ", passround='" + passround + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", account='" + account + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
