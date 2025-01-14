package com.example.seminar_13;

import java.io.Serializable;

public class Student implements Serializable
{
    private String nume;
    private int varsta;
    private int nrMatricol;
    private boolean disponibil;

    public Student(String nume, int varsta, int nrMatricol, boolean disponibil) {
        this.nume = nume;
        this.varsta = varsta;
        this.nrMatricol = nrMatricol;
        this.disponibil = disponibil;
    }

    public Student(String nume, int varsta, int nrMatricol) {
        this(nume, varsta, nrMatricol, false);
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public int getNrMatricol() {
        return nrMatricol;
    }

    public void setNrMatricol(int nrMatricol) {
        this.nrMatricol = nrMatricol;
    }

    public boolean isDisponibil() {
        return disponibil;
    }

    public void setDisponibil(boolean disponibil) {
        this.disponibil = disponibil;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", varsta=").append(varsta);
        sb.append(", nrMatricol=").append(nrMatricol);
        sb.append(", disponibil=").append(disponibil);
        sb.append('}');
        return sb.toString();
    }
}
