package com.example.roomproj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewAstronautaActivity.class);
                startActivityForResult(intent, NEW_ASTRONAUTA_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        outerIf:
        if (requestCode == NEW_ASTRONAUTA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            for (Astronauta a : mAstronautaViewModel.getAstronautasList().getValue()) {
                if(a.name.equals(data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_NAME))){
                    Toast.makeText(
                            getApplicationContext(),
                            "An astronauta with that name already exists.",
                            Toast.LENGTH_LONG).show();
                    break outerIf;
                }
            }
            Astronauta astro = new Astronauta(data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_NAME), data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_ADDRESS), data.getIntExtra(NewAstronautaActivity.EXTRA_REPLY_AGE, 0));
            mAstronautaViewModel.insert(astro);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
