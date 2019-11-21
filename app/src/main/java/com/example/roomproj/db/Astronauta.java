package com.example.roomproj.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "astronautas")
public class Astronauta {

    public Astronauta(@NonNull String name, String address, int age) {

        this.name = name;
        this.address = address;
        this.age = age;

    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @NonNull
    public String getName() {
        return this.name;
    }

    @ColumnInfo(name = "address")
    @NonNull
    public String address;

    @NonNull
    public String getAddress() {
        return this.address;
    }

    @ColumnInfo(name = "age")
    @NonNull
    public int age;

    @NonNull
    public int getAge() {
        return this.age;
    }

}
