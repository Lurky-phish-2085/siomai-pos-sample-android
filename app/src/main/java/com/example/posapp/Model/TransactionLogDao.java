package com.example.posapp.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionLogDao {
    @Insert
    void insert(TransactionLog transactionLog);

    @Update
    void update(TransactionLog transactionLog);

    @Delete
    void delete(TransactionLog transactionLog);

    @Query("DELETE FROM transaction_table")
    void deleteAll();

    @Query("SELECT * FROM transaction_table")
    LiveData<List<TransactionLog>> getAllTransactions();
}
