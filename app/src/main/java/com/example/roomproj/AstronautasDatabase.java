package com.example.roomproj;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                AstronautaDao dao = INSTANCE.astronautaDao();
                dao.deleteAll();

                Astronauta as = new Astronauta("asd", "c/ false 123", 2);
                dao.insert(as);
                as = new Astronauta("gsdgfsgf", "c/ true 123", 45);
                dao.insert(as);
            });
        }
    };
}
