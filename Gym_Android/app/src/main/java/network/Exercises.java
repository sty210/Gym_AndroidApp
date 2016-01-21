package network;

import java.util.List;

/**
 * Created by elite on 16. 1. 20..
 */
public class Exercises {
    public int id;
    public int ex_det_cd;
    public String cd_nm;
    public String ex_mth_img;
    public String ex_mth_ep;

    public String getCdNm(){
        return this.cd_nm;
    }
    public void setCdNm(String cd_nm){
        this.cd_nm = cd_nm;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getExDetCd(){
        return ex_det_cd;
    }
    public void setExDetCd(int ex_det_cd){
        this.ex_det_cd = ex_det_cd;
    }
    public String getExMthImg(){
        return ex_mth_img;
    }
    public void setExMthImg(String ex_mth_img){
        this.ex_mth_img = ex_mth_img;
    }
    public String getExMthEp(){
        return ex_mth_ep;
    }
    public void setExMthEp(String ex_mth_ep){
        this.ex_mth_ep = ex_mth_ep;
    }
}
