package network;

/**
 * Created by elite on 16. 1. 21..
 */
public class Gyms {
    public int id;
    public int rgn_cd;
    public String gym_nm;
    public String gym_img;
    public String gym_adr;
    public String gym_tel;


    public void setId(int id) {
        this.id = id;
    }

    public void setRgn_cd(int rgn_cd) {
        this.rgn_cd = rgn_cd;
    }

    public void setGym_nm(String gym_nm) {
        this.gym_nm = gym_nm;
    }

    public void setGym_img(String gym_img) {
        this.gym_img = gym_img;
    }

    public void setGym_adr(String gym_adr) {
        this.gym_adr = gym_adr;
    }

    public void setGym_tel(String gym_tel) {
        this.gym_tel = gym_tel;
    }

    public int getId() {
        return id;
    }

    public int getRgn_cd() {
        return rgn_cd;
    }

    public String getGym_nm() {
        return gym_nm;
    }

    public String getGym_img() {
        return gym_img;
    }

    public String getGym_adr() {
        return gym_adr;
    }

    public String getGym_tel() {
        return gym_tel;
    }
}
