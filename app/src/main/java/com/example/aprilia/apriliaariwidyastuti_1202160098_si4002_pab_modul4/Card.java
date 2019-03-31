package com.example.aprilia.apriliaariwidyastuti_1202160098_si4002_pab_modul4;

public class Card  {
    String image,nama,harga,deskripsi;

    public Card(String image, String nama, String harga, String deskripsi) {
        this.image = image;
        this.nama = nama;
        this.harga = harga;
        this.deskripsi = deskripsi;
    }

    public String getImagePath() {
        return image;
    }

    public void setImagePath(String imagePath) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
}
