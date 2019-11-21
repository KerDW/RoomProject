package com.example.roomproj.astronauta;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomproj.db.Astronauta;
import com.example.roomproj.db.AstronautaDao;
import com.example.roomproj.db.AstronautasDatabase;

import java.util.List;

public class AstronautasViewModel extends AndroidViewModel {
    private AstronautaDao astronautaDao;
    private LiveData<List<Astronauta>> astronautasLiveData;

    public AstronautasViewModel(@NonNull Application application) {
        super(application);
        astronautaDao = AstronautasDatabase.getDatabase(application).astronautaDao();
        astronautasLiveData = astronautaDao.getAllAstronautas();
    }

    public LiveData<List<Astronauta>> getAstronautasList() {
        return astronautasLiveData;
    }

    public void insert(Astronauta... astronautas) {
        astronautaDao.insert(astronautas);
    }

    public void update(Astronauta astronauta) {
        astronautaDao.update(astronauta);
    }

    public void deleteAll() {
        astronautaDao.deleteAll();
    }
}
