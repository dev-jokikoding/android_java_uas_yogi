package com.example.uas_yogiantosyahputra_2110031806052.model;

public class Barang {
    Integer id;
    String nama;
    String alamat;
    String no_penjual;
    String kode_barang;
    Integer jumlah_penjualan;
    Integer harga_satuan;
    Integer diskon;
    Integer total_harga;

    public Barang(Integer id, String nama, String alamat, String no_penjual, String kode_barang, Integer jumlah_penjualan, Integer harga_satuan, Integer diskon, Integer total_harga) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.no_penjual = no_penjual;
        this.kode_barang = kode_barang;
        this.jumlah_penjualan = jumlah_penjualan;
        this.harga_satuan = harga_satuan;
        this.diskon = diskon;
        this.total_harga = total_harga;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_penjual() {
        return no_penjual;
    }

    public void setNo_penjual(String no_penjual) {
        this.no_penjual = no_penjual;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public Integer getJumlah_penjualan() {
        return jumlah_penjualan;
    }

    public void setJumlah_penjualan(Integer jumlah_penjualan) {
        this.jumlah_penjualan = jumlah_penjualan;
    }

    public Integer getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(Integer harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public Integer getDiskon() {
        return diskon;
    }

    public void setDiskon(Integer diskon) {
        this.diskon = diskon;
    }

    public Integer getTotal_harga() {
        return total_harga;
    }

    public void setTotal_harga(Integer total_harga) {
        this.total_harga = total_harga;
    }
}
