package com.desarrollo.roomdataloja.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.desarrollo.roomdataloja.repositorio.AnimalRepositorio;
import com.desarrollo.roomdataloja.roomdatabase.Animal;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel {

    private AnimalRepositorio animalRepositorio;

    private LiveData<List<Animal>> mAllAnimales;

    public AnimalViewModel(@NonNull Application application) {
        super(application);
        animalRepositorio = new AnimalRepositorio(application);
        mAllAnimales = animalRepositorio.getAllAnimales();
    }

    public LiveData<List<Animal>> getmAllAnimales(){return mAllAnimales;}

    public void insert(Animal animal){ animalRepositorio.insert(animal);}
}
