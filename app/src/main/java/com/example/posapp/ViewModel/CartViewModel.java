package com.example.posapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.posapp.Model.Cart;
import com.example.posapp.Model.MainRepository;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    MainRepository mainRepository;
    public LiveData<List<Cart>> allCart;


    public CartViewModel(@NonNull Application application) {
        super(application);
        mainRepository = new MainRepository(application);
        allCart = mainRepository.getAllCart();
    }

    public void insert(Cart cart) {
        System.out.println("From View Model");
        System.out.println(cart);
        mainRepository.insert(cart);
    }

    public void update(Cart cart) {
        mainRepository.update(cart);
    }

    public void delete(Cart cart) {
        mainRepository.delete(cart);
    }

    public void deleteAllCart() {
        mainRepository.deleteAllCart();
    }

    public LiveData<List<Cart>> getAllCart() {
        return allCart;
    }
}
