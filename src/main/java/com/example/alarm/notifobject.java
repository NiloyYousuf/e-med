package com.example.alarm;

public class notifobject {
    private String mednem, dosunit, timez, type, weekde;
    private int dos;

    public notifobject()
    {

    }

    public notifobject(String mednem, String dosunit, String timez, String type, int dos) {
        this.mednem = mednem;
        this.dosunit = dosunit;
        this.timez = timez;
        this.type = type;
        this.dos = dos;
    }

    public String getMednem() {
        return mednem;
    }

    public void setMednem(String mednem) {
        this.mednem = mednem;
    }

    public void setWeekde(String wk) { this.weekde = wk; }
    public String getWeekde() { return this.weekde ;}

    public String getDosunit() {
        return dosunit;
    }

    public void setDosunit(String dosunit) {
        this.dosunit = dosunit;
    }

    public String getTimez() {
        return timez;
    }

    public void setTimez(String timez) {
        this.timez = timez;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDos() {
        return dos;
    }

    public void setDos(int dos) {
        this.dos = dos;
    }
}
