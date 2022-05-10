package com.example.projekat;

public class UslugaModel {

    protected String tip;
    protected String kolicina;

    public UslugaModel(String tip, String kolicina) {
        this.tip = tip;
        this.kolicina = kolicina;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }
}
