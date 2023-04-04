package com.example.posapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Siomai_InventoryDao {
    @Insert
    void insert(Siomai_Inventory siomai_inventory);

    @Update
    void update(Siomai_Inventory siomai_inventory);

    @Delete
    void delete(Siomai_Inventory siomai_inventory);

    @Query("DELETE FROM siomai_inventory_table")
    void deleteAll();

    @Query("SELECT * FROM siomai_inventory_table")
    LiveData<List<Siomai_Inventory>> getAllSiomai();
}
