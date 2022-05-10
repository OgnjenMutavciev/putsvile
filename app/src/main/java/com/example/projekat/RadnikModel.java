package com.example.projekat;

public class RadnikModel {

    protected String naziv;
    protected String cena;
    protected String ocena;

    public RadnikModel(String naziv, String cena, String ocena) {
        this.naziv = naziv;
        this.cena = cena;
        this.ocena = ocena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }
}
