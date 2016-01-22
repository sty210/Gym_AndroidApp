package com.baemin.sun.Adapters;

/**
 * Created by elite on 16. 1. 19..
 */
public class RowData {
    public int id;
    public String name;
    public String imgUrl;
    public String exmth;


    public void setExmth(String exmth) {
        this.exmth = exmth;
    }

    public String getExmth() {
        return exmth;
    }

    public RowData(int id, String name, String imgUrl, String exmth) {
        this.id=id;
        this.name= name;
        this.imgUrl=imgUrl;
        this.exmth=exmth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }



    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
