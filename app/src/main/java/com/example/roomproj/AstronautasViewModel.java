package com.example.roomproj;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public void delete (Astronauta astronauta) { astronautaDao.delete(astronauta); }

    public void update(Astronauta astronauta) {
        astronautaDao.update(astronauta);
    }

    public void deleteAll() {
        astronautaDao.deleteAll();
    }
}
