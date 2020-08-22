package com.desarrollo.roomdataloja;

import android.content.Intent;
import android.os.Bundle;

import com.desarrollo.roomdataloja.adaptador.AnimalListAdapter;
import com.desarrollo.roomdataloja.roomdatabase.Animal;
import com.desarrollo.roomdataloja.viewmodel.AnimalViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AnimalViewModel animalViewModel;
    public static final int NUEVO_ANIMAL_RC = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AnimalListAdapter adapter = new AnimalListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        animalViewModel = ViewModelProviders.of(this).get(AnimalViewModel.class);

        animalViewModel.getmAllAnimales().observe(this, new Observer<List<Animal>>() {
            @Override
            public void onChanged(List<Animal> animales) {
                adapter.setAnimales(animales);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent,NUEVO_ANIMAL_RC);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == NUEVO_ANIMAL_RC && resultCode == RESULT_OK){
            Animal animal = new Animal(data.getStringExtra(MainActivity2.RPT_EXTRA));
            animalViewModel.insert(animal);
        }else{
            Toast.makeText(getApplicationContext(), "NO HAY DATOS", Toast.LENGTH_SHORT).show();
        }
    }

}
