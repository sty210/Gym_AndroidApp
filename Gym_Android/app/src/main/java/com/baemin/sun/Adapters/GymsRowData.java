package com.baemin.sun.Adapters;

/**
 * Created by elite on 16. 1. 21..
 */
public class GymsRowData {
    public int id;
    public String gymName;
    public String imgUrl;
    public String gymTel;
    public String gymAddress;
    public int rgnCd;
    public String gymExplanation;


    public GymsRowData(int id, String gymName, String imgUrl, String gymAddress, String gymTel, int rgnCd, String gymExplanation) {
        this.id=id;
        this.gymName= gymName;
        this.imgUrl=imgUrl;
        this.gymTel=gymTel;
        this.gymAddress = gymAddress;
        this.rgnCd = rgnCd;
        this.gymExplanation = gymExplanation;
    }

    public void setGymExplanation(String gymExplanation) {
        this.gymExplanation = gymExplanation;
    }

    public String getGymExplanation() {

        return gymExplanation;
    }

    public int getId() {
        return id;
    }

    public String getGymName() {
        return gymName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getGymTel() {
        return gymTel;
    }

    public String getGymAddress() {
        return gymAddress;
    }

    public int getRgnCd() {
        return rgnCd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setGymTel(String gymTel) {
        this.gymTel = gymTel;
    }

    public void setGymAddress(String gymAddress) {
        this.gymAddress = gymAddress;
    }

    public void setRgnCd(int rgnCd) {
        this.rgnCd = rgnCd;
    }
}
