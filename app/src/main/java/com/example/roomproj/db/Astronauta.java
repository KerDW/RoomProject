package com.example.roomproj.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "astronautas")
public class Astronauta {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "address")
    @NonNull
    public String address;

    @ColumnInfo(name = "age")
    @NonNull
    public int age;

}
