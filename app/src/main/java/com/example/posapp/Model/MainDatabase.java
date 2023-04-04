package com.example.posapp.Model;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.posapp.R;

@Database(entities = {Cart.class, Siomai_Inventory.class, TransactionLog.class}, version = 1)
public abstract class MainDatabase extends RoomDatabase {
    private static MainDatabase instance;
    public abstract Siomai_InventoryDao siomai_inventoryDao();
    public abstract CartDao cartDao();
    public abstract TransactionLogDao transactionLogDao();

    public static synchronized MainDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MainDatabase.class, "MainDatabase4")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PrepopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PrepopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private Siomai_InventoryDao siomai_inventoryDao;
        private CartDao cartDao;

        private PrepopulateDBAsyncTask(MainDatabase db){
            cartDao = db.cartDao();
            siomai_inventoryDao = db.siomai_inventoryDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            System.out.println("Carl Where Are you??");
            cartDao.insert(new Cart("Beef", R.drawable.beef_icon, 35.00, 5 ));
            cartDao.insert(new Cart("Pork", R.drawable.pork_icon, 25.00, 5 ));
            cartDao.insert(new Cart("Crab", R.drawable.crab_icon, 40.00, 10 ));


            siomai_inventoryDao.insert(new Siomai_Inventory("Beef", 35, 100, R.drawable.beef_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Japanese", 40, 100, R.drawable.crab_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Pork", 35, 100, R.drawable.pork_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Shark Fin", 40, 100, R.drawable.sharksfin_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Crab", 45, 100, R.drawable.crab_icon));

            siomai_inventoryDao.insert(new Siomai_Inventory("Beef", 35, 100, R.drawable.beef_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Japanese", 40, 100, R.drawable.crab_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Pork", 35, 100, R.drawable.pork_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Shark Fin", 40, 100, R.drawable.sharksfin_icon));
            siomai_inventoryDao.insert(new Siomai_Inventory("Crab", 45, 100, R.drawable.crab_icon));


            return null;
        }
    }
}
