package com.baemin.sun.gym_android;

/**
 * Created by elite on 16. 1. 19..
 */
public class RowData {
    int id;
    String name;
    int imgId;      //운동이나 헬스장 이미지의 리소스 아이디

    public RowData(int id, String name, int imgId) {
        this.id=id;
        this.name= name;
        this.imgId=imgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getImgId() {
        return imgId;
    }
}
