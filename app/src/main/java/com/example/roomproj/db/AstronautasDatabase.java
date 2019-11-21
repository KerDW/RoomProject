package com.example.roomproj.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Astronauta.class}, version = 1, exportSchema = false)
public abstract class AstronautasDatabase extends RoomDatabase {

    public abstract AstronautaDao astronautaDao();

    private static volatile AstronautasDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AstronautasDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AstronautasDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AstronautasDatabase.class, "astronautas_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
