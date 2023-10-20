package com.example.duanmau_ph44245.model;

public class Sach {
    public int maSach;
    public String tenSach;
    public int namXB;
    public int giaThue;
    public int maLoai;
    private String tenLoai;


    public Sach() {
    }

    public Sach(int maSach, String tenSach, int namXB, int giaThue, int maLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.namXB = namXB;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
    }

    public Sach(int maSach, String tenSach, int namXB, int giaThue, int maLoai, String tenLoai) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.namXB = namXB;
        this.giaThue = giaThue;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
