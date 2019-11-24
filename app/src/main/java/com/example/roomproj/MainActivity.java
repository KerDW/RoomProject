package com.example.roomproj;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AstronautaListAdapter.OnItemClicked {

    private AstronautasViewModel mAstronautaViewModel;
    public static final int NEW_ASTRONAUTA_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_ASTRONAUTA_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AstronautaListAdapter adapter = new AstronautaListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnClick(MainActivity.this);

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

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, NewAstronautaActivity.class);
        Astronauta astro = mAstronautaViewModel.getAstronautasList().getValue().get(position);
        intent.putExtra("ASTRO", astro);
        startActivityForResult(intent, EDIT_ASTRONAUTA_ACTIVITY_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ASTRONAUTA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Astronauta astro = new Astronauta(data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_NAME), data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_ADDRESS), data.getIntExtra(NewAstronautaActivity.EXTRA_REPLY_AGE, 0));
            mAstronautaViewModel.insert(astro);
        } else if(requestCode == EDIT_ASTRONAUTA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){

            Astronauta astro = new Astronauta(data.getIntExtra(NewAstronautaActivity.EXTRA_REPLY_ID, 0), data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_NAME), data.getStringExtra(NewAstronautaActivity.EXTRA_REPLY_ADDRESS), data.getIntExtra(NewAstronautaActivity.EXTRA_REPLY_AGE, 0));
            mAstronautaViewModel.update(astro);

        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}
