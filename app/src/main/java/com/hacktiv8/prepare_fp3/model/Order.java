package com.hacktiv8.prepare_fp3.model;

public class Order {
    private String id, tglPesan, waktuPesan, noBooking, bus, nopol, waktuBus, terminal, kotaAwal;

    public Order(String id, String tglPesan, String waktuPesan, String noBooking, String bus, String nopol, String waktuBus, String terminal, String kotaAwal) {
        this.id = id;
        this.tglPesan = tglPesan;
        this.waktuPesan = waktuPesan;
        this.noBooking = noBooking;
        this.bus = bus;
        this.nopol = nopol;
        this.waktuBus = waktuBus;
        this.terminal = terminal;
        this.kotaAwal = kotaAwal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTglPesan() {
        return tglPesan;
    }

    public void setTglPesan(String tglPesan) {
        this.tglPesan = tglPesan;
    }

    public String getWaktuPesan() {
        return waktuPesan;
    }

    public void setWaktuPesan(String waktuPesan) {
        this.waktuPesan = waktuPesan;
    }

    public String getNoBooking() {
        return noBooking;
    }

    public void setNoBooking(String noBooking) {
        this.noBooking = noBooking;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getWaktuBus() {
        return waktuBus;
    }

    public void setWaktuBus(String waktuBus) {
        this.waktuBus = waktuBus;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getKotaAwal() {
        return kotaAwal;
    }

    public void setKotaAwal(String kotaAwal) {
        this.kotaAwal = kotaAwal;
    }

    //    public String getKota() {
//        return kota;
//    }
//
//    public void setKota(String kota) {
//        this.kota = kota;
//    }
//
//    public String getStatusPembayaran() {
//        return statusPembayaran;
//    }
//
//    public void setStatusPembayaran(String statusPembayaran) {
//        this.statusPembayaran = statusPembayaran;
//    }
}
