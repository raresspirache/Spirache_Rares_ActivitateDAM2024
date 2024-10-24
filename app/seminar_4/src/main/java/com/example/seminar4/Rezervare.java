package com.example.seminar4;

public class Rezervare
{
    private int id;
    private String activitate;
    private String nume_client;
    private boolean stare;

    public Rezervare(String activitate, boolean stare, String nume_client, int id) {
        this.activitate = activitate;
        this.stare = stare;
        this.nume_client = nume_client;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivitate() {
        return activitate;
    }

    public void setActivitate(String activitate) {
        this.activitate = activitate;
    }

    public String getNume_client() {
        return nume_client;
    }

    public void setNume_client(String nume_client) {
        this.nume_client = nume_client;
    }

    public boolean getStare() {
        return stare;
    }

    public void setStare(boolean stare) {
        this.stare = stare;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Rezervare{");
        sb.append("id=").append(id);
        sb.append(", activitate='").append(activitate).append('\'');
        sb.append(", nume_client='").append(nume_client).append('\'');
        sb.append(", stare='").append(stare).append('\'');
        sb.append('}');
        return sb.toString();
    }


}
