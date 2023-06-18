package com.hacktiv8.prepare_fp3.model;

public class Destination {
    private String id, kota, terminal, terminal_short;

    public Destination(){

    }

    public Destination(String kota, String terminal){
        this.kota = kota;
        this.terminal = terminal;
    }

//    public Destination(String id, String kota, String terminal_short){
//        this.id = id;
//        this.kota = kota;
//        this.terminal_short = terminal_short;
//    }

    public Destination(String id, String kota, String terminal, String terminal_short) {
        this.id = id;
        this.kota = kota;
        this.terminal = terminal;
        this.terminal_short = terminal_short;
    }

    public Destination(String kota, String terminal, String terminal_short) {
        this.kota = kota;
        this.terminal = terminal;
        this.terminal_short = terminal_short;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getTerminal_short() {
        return terminal_short;
    }

    public void setTerminal_short(String terminal_short) {
        this.terminal_short = terminal_short;
    }
}
