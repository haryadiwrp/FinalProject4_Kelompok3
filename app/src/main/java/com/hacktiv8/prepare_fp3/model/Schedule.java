package com.hacktiv8.prepare_fp3.model;

public class Schedule {
    private String id, bus, arrival_city, arrival_time, arrival_cityTerminal, departure_city, departure_time, departure_cityTerminal, nopol;
    private String harga, rating, jumlah_review;
    //private int harga, rating, jumlah_review;

    public Schedule(){

    }

    //komplit
    public Schedule(String id, String bus, String nopol, String departure_time, String arrival_time, String departure_city, String arrival_city, String departure_cityTerminal, String arrival_cityTerminal, String harga, String rating, String jumlah_review) {
        this.id = id;
        this.bus = bus;//
        this.nopol = nopol;
        this.departure_time = departure_time;//
        this.arrival_time = arrival_time;//
        this.departure_city = departure_city;//
        this.arrival_city = arrival_city;//
        this.departure_cityTerminal = departure_cityTerminal;
        this.arrival_cityTerminal = arrival_cityTerminal;
        this.harga = harga;
        this.rating = rating;
        this.jumlah_review = jumlah_review;
    }

    public Schedule(String bus, String nopol, String departure_time, String arrival_time, String departure_city, String arrival_city, String departure_cityTerminal, String arrival_cityTerminal, String harga, String rating, String jumlah_review) {
        this.bus = bus;//
        this.nopol = nopol;
        this.departure_time = departure_time;//
        this.arrival_time = arrival_time;//
        this.departure_city = departure_city;//
        this.arrival_city = arrival_city;//
        this.departure_cityTerminal = departure_cityTerminal;
        this.arrival_cityTerminal = arrival_cityTerminal;
        this.harga = harga;
        this.rating = rating;
        this.jumlah_review = jumlah_review;
    }

//    public Schedule(String bus, String arrival_city, String arrival_time, String departure_city, String departure_time){
//        this.bus = bus;
//        this.arrival_city = arrival_city;
//        this.arrival_time = arrival_time;
//        this.departure_city = departure_city;
//        this.departure_time = departure_time;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_cityTerminal() {
        return arrival_cityTerminal;
    }

    public void setArrival_cityTerminal(String arrival_cityTerminal) {
        this.arrival_cityTerminal = arrival_cityTerminal;
    }

    public String getDeparture_cityTerminal() {
        return departure_cityTerminal;
    }

    public void setDeparture_cityTerminal(String departure_cityTerminal) {
        this.departure_cityTerminal = departure_cityTerminal;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getJumlah_review() {
        return jumlah_review;
    }

    public void setJumlah_review(String jumlah_review) {
        this.jumlah_review = jumlah_review;
    }
}
