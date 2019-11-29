package com.example.nikhil.musictron;

/**
 * Created by Nikhil on 11/15/2019.
 */

public class data {

    String nam;
    String em;
    String pass;
    String pn;
    String checkin;

    public data(){

    }

    public data(String nam, String em, String pass, String pn, String checkin){
        this.nam = nam;
        this.em = em;
        this.pass = pass;
        this.pn = pn;
        this.checkin = checkin;
    }

    public String getNam() {
        return nam;
    }

    public String getEm() {
        return em;
    }

    public String getPass() {
        return pass;
    }

    public String getPn() {
        return pn;
    }

    public  String getCheckin(){ return checkin; }
}
