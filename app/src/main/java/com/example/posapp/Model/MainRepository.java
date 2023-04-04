package com.example.posapp.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MainRepository {
    private Siomai_InventoryDao siomai_inventoryDao;
    private CartDao cartDao;
    private TransactionLogDao transactionLogDao;

    private LiveData<List<Siomai_Inventory>> allSiomai;
    private LiveData<List<Cart>> allCart;
    private LiveData<List<TransactionLog>> allTransactionLogs;

    public MainRepository(Application application){
        MainDatabase database = MainDatabase.getInstance(application);
        siomai_inventoryDao = database.siomai_inventoryDao();
        cartDao = database.cartDao();
        transactionLogDao = database.transactionLogDao();

        allSiomai = siomai_inventoryDao.getAllSiomai();
        allCart = cartDao.getAllCart();
        allTransactionLogs = transactionLogDao.getAllTransactions();
    }

    //region Insertion Functions
    public void insert(Siomai_Inventory siomai_inventory){
        new AsyncRepoTasks(siomai_inventoryDao, siomai_inventory, 1).execute();
    }

    public void insert(Cart cart){
        new AsyncRepoTasks(cartDao, cart, 5).execute();
    }

    public void insert(TransactionLog transactionLog){
        new AsyncRepoTasks(transactionLogDao, transactionLog, 9).execute();
    }

    //endregion

    //region Updating Functions
    public void update(Siomai_Inventory siomai_inventory){
        new AsyncRepoTasks(siomai_inventoryDao, siomai_inventory, 2).execute();
    }

    public void update(Cart cart){
        new AsyncRepoTasks(cartDao, cart, 6).execute();

    }

    public void update(TransactionLog transactionLog){
        new AsyncRepoTasks(transactionLogDao, transactionLog, 10).execute();
    }
    //endregion

    //region Deleting one function
    public void delete(Siomai_Inventory siomai_inventory){
        new AsyncRepoTasks(siomai_inventoryDao, siomai_inventory, 3).execute();
    }

    public void delete(Cart cart){
        new AsyncRepoTasks(cartDao, cart, 7).execute();
    }

    public void delete(TransactionLog transactionLog){
        new AsyncRepoTasks(transactionLogDao, transactionLog, 11).execute();
    }
    //endregion

    //region Delete all
    public void deleteAllSiomai(){
        new AsyncRepoTasks(siomai_inventoryDao, new Siomai_Inventory(), 4).execute();
    }

    public void deleteAllCart(){
        new AsyncRepoTasks(cartDao, new Cart(), 8).execute();

    }

    public void deleteAllTransactions(){
        new AsyncRepoTasks(transactionLogDao, new TransactionLog(), 12).execute();
    }
    //endregion

    //region GetData

    public LiveData<List<Siomai_Inventory>> getAllSiomai() {
        return allSiomai;
    }

    public LiveData<List<Cart>> getAllCart() {
        return allCart;
    }

    public LiveData<List<TransactionLog>> getAllTransactionLogs() {
        return allTransactionLogs;
    }


    //Endregion



    private static class AsyncRepoTasks extends AsyncTask<Void, Void, Void>{
        private Siomai_InventoryDao siomai_inventoryDao;
        private Siomai_Inventory siomaiInventory;
        private CartDao cartDao;
        private Cart cart;
        private TransactionLogDao transactionLogDao;
        private TransactionLog transactionLog;
        private int mode;


        public AsyncRepoTasks(Siomai_InventoryDao siomai_inventoryDao, Siomai_Inventory siomaiInventory, int mode) {
            this.siomai_inventoryDao = siomai_inventoryDao;
            this.siomaiInventory = siomaiInventory;
            this.mode = mode;
        }

        public AsyncRepoTasks(CartDao cartDao, Cart cart, int mode) {
            this.cartDao = cartDao;
            this.cart = cart;
            this.mode = mode;
        }

        public AsyncRepoTasks(TransactionLogDao transactionLogDao, TransactionLog transactionLog, int mode) {
            this.transactionLogDao = transactionLogDao;
            this.transactionLog = transactionLog;
            this.mode = mode;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            switch (mode) {
                //Siomai
                case 1:
                    siomai_inventoryDao.insert(siomaiInventory);
                    break;
                case 2:
                    siomai_inventoryDao.update(siomaiInventory);
                    break;
                case 3:
                    siomai_inventoryDao.delete(siomaiInventory);
                    break;
                case 4:
                    siomai_inventoryDao.deleteAll();
                    break;
                //Cart
                case 5:
                    cartDao.insert(cart);
                    break;
                case 6:
                    cartDao.update(cart);
                    break;
                case 7:
                    cartDao.delete(cart);
                    break;
                case 8:
                    cartDao.deleteAll();
                    break;
                //Transaction
                case 9:
                    transactionLogDao.insert(transactionLog);
                    break;
                case 10:
                    transactionLogDao.update(transactionLog);
                    break;
                case 11:
                    transactionLogDao.delete(transactionLog);
                    break;
                case 12:
                    transactionLogDao.deleteAll();
                    break;
            }
            return null;
        }
    }
}
