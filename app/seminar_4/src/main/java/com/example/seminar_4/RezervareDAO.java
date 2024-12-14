package com.example.seminar_4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RezervareDAO
{
    void insertRezervare(Rezervare rezervare);

    @Query("SELECT * FROM Rezervari")
    List<Rezervare> getAllRezervari();

    @Delete
    void deleteRezervare(Rezervare rezervare);
}
