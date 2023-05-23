package com.rippleInv.sidemung.Model;

public class Pengaduan {
    private  String judul, status,alamat,description,id,image;

    public Pengaduan(String judul, String status, String alamat, String description, String id, String image) {
        this.judul = judul;
        this.status = status;
        this.alamat = alamat;
        this.description = description;
        this.id = id;
        this.image = image;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
