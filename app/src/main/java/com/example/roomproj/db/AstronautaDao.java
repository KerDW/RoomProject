package com.example.roomproj.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AstronautaDao {

    @Query("SELECT * FROM astronautas WHERE id = :id LIMIT 1")
    Astronauta findAstronautaById(int id);

    @Query("SELECT * FROM astronautas WHERE name = :name LIMIT 1")
    Astronauta findAstronautaByName(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Astronauta astronauta);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Astronauta... astronautas);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Astronauta astronauta);

    @Query("DELETE FROM astronautas")
    void deleteAll();

    @Query("SELECT * FROM astronautas ORDER BY name ASC")
    LiveData<List<Astronauta>> getAllAstronautas();

}
