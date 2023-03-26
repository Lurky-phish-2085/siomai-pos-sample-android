package com.example.posapp;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductViewModel extends ViewModel {
    //Currently Selected
    private final MutableLiveData<String> selectedItem = new MutableLiveData<>();
    public void setSelectedItem(String string){
        //Mutator
        selectedItem.setValue(string);
    }
    public LiveData<String> getSelectedItem(){
        return selectedItem;
    }


}
