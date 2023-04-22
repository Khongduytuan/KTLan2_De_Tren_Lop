package com.example.ktlan2_sqlite_tablayout.model;

import java.io.Serializable;

public class CongViec implements Serializable {
    private int id;
    private String tenCV, noiDungCV, ngayHoanThanh, tinhTrang, congTac;

    public CongViec() {
    }

    public CongViec(int id, String tenCV, String noiDungCV, String ngayHoanThanh, String tinhTrang, String congTac) {
        this.id = id;
        this.tenCV = tenCV;
        this.noiDungCV = noiDungCV;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhTrang = tinhTrang;
        this.congTac = congTac;
    }

    public CongViec(String tenCV, String noiDungCV, String ngayHoanThanh, String tinhTrang, String congTac) {
        this.tenCV = tenCV;
        this.noiDungCV = noiDungCV;
        this.ngayHoanThanh = ngayHoanThanh;
        this.tinhTrang = tinhTrang;
        this.congTac = congTac;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getNoiDungCV() {
        return noiDungCV;
    }

    public void setNoiDungCV(String noiDungCV) {
        this.noiDungCV = noiDungCV;
    }

    public String getNgayHoanThanh() {
        return ngayHoanThanh;
    }

    public void setNgayHoanThanh(String ngayHoanThanh) {
        this.ngayHoanThanh = ngayHoanThanh;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getCongTac() {
        return congTac;
    }

    public void setCongTac(String congTac) {
        this.congTac = congTac;
    }
}
