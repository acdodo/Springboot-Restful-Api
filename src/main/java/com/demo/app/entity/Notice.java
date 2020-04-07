package com.demo.app.entity;

import java.io.Serializable;
import java.util.Date;

public class Notice implements Serializable {

    private static final long serialVersionUID = -3158957272338343688L;
    private int id;
    private String name;
    private Date time;
    private String dr;
    private String verifier;
    private String notes;
    private String detailstwo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDr() {
        return dr;
    }

    public void setDr(String dr) {
        this.dr = dr;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDetailstwo() {
        return detailstwo;
    }

    public void setDetailstwo(String detailstwo) {
        this.detailstwo = detailstwo;
    }

    @Override
    public String toString() {
        return "OfficialDoc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", dr='" + dr + '\'' +
                ", verifier='" + verifier + '\'' +
                ", notes='" + notes + '\'' +
                ", detailstwo='" + detailstwo + '\'' +
                '}';
    }
}
