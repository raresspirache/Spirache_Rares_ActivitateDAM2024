package com.example.seminar_4;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Rezervare.class}, version = 1)
public abstract class RezervariDataBase extends RoomDatabase
{
    public abstract RezervareDAO getRezervareDAO();
}
