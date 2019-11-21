package com.example.roomproj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AstronautasViewModel mAstronautaViewModel;
    public static final int NEW_ASTRONAUTA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AstronautaListAdapter adapter = new AstronautaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAstronautaViewModel = new ViewModelProvider(this).get(AstronautasViewModel.class);

        mAstronautaViewModel.getAstronautasList().observe(this, new Observer<List<Astronauta>>() {
            @Override
            public void onChanged(@Nullable final List<Astronauta> astros) {
                // Update the cached copy of the words in the adapter.
                adapter.setAstronautas(astros);
            }
        });
    }
}
