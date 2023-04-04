package com.example.posapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.posapp.Model.Cart;
import com.example.posapp.Model.MainRepository;
import com.example.posapp.Model.Siomai_Inventory;

import java.util.List;

public class SiomaiViewModel extends AndroidViewModel {

    MainRepository mainRepository;
    public LiveData<List<Siomai_Inventory>> allSiomai;


    public SiomaiViewModel(@NonNull Application application) {
        super(application);
        mainRepository = new MainRepository(application);
        allSiomai = mainRepository.getAllSiomai();
    }

    public void insert(Siomai_Inventory siomai_inventory) {
        mainRepository.insert(siomai_inventory);
    }

    public void update(Siomai_Inventory siomai_inventory) {
        mainRepository.update(siomai_inventory);
    }

    public void delete(Siomai_Inventory siomai_inventory) {
        mainRepository.delete(siomai_inventory);
    }

    public void deleteAllSiomai() {
        mainRepository.deleteAllSiomai();
    }

    public LiveData<List<Siomai_Inventory>> getAllSiomai() {
        return allSiomai;
    }
}
