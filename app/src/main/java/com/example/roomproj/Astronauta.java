package com.example.roomproj;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "astronautas")
public class Astronauta implements Serializable {

    @Ignore
    public Astronauta(@NonNull int id, @NonNull String name, String address, int age) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;

    }

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
