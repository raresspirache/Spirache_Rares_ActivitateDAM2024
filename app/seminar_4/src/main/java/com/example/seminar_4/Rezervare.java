package com.example.seminar_4;

import android.os.Parcel;
import android.os.Parcelable;

public class Rezervare implements Parcelable {
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

    protected Rezervare(Parcel in) {
        id = in.readInt();
        activitate = in.readString();
        nume_client = in.readString();
        stare = in.readByte() != 0;
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
        sb.append(", stare=").append(stare);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(activitate);
        dest.writeString(nume_client);
        dest.writeByte((byte) (stare ? 1 : 0));
    }

    public static final Creator<Rezervare> CREATOR = new Creator<Rezervare>() {
        @Override
        public Rezervare createFromParcel(Parcel in) {
            return new Rezervare(in);
        }

        @Override
        public Rezervare[] newArray(int size) {
            return new Rezervare[size];
        }
    };
}
