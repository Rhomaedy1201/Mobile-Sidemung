package com.rippleInv.sidemung.Model;

public class Pengaduan {
    private  String judul, status,alamat,description;

    public Pengaduan(String judul, String status, String alamat, String description) {
        this.judul = judul;
        this.status = status;
        this.alamat = alamat;
        this.description = description;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
