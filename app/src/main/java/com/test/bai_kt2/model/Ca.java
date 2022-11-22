package com.test.bai_kt2.model;

import java.io.Serializable;

public class Ca implements Serializable {
    private String id;
    private String TenKH;
    private String TenTGoi;
    private String DacTinh;
    private String MauSac;

    public Ca() {
    }

    @Override
    public String toString() {
        return "Ca{" +
                "id='" + id + '\'' +
                ", TenKH='" + TenKH + '\'' +
                ", TenTGoi='" + TenTGoi + '\'' +
                ", DacTinh='" + DacTinh + '\'' +
                ", MauSac='" + MauSac + '\'' +
                '}';
    }

    public Ca(String tenKH, String tenTGoi, String dacTinh, String mauSac) {
        TenKH = tenKH;
        TenTGoi = tenTGoi;
        DacTinh = dacTinh;
        MauSac = mauSac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getTenTGoi() {
        return TenTGoi;
    }

    public void setTenTGoi(String tenTGoi) {
        TenTGoi = tenTGoi;
    }

    public String getDacTinh() {
        return DacTinh;
    }

    public void setDacTinh(String dacTinh) {
        DacTinh = dacTinh;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String mauSac) {
        MauSac = mauSac;
    }

}
