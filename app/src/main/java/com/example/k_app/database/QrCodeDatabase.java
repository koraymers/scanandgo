package com.example.k_app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {QrCodeModel.class}, version = 1, exportSchema = false)
public abstract class QrCodeDatabase extends RoomDatabase {

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile QrCodeDatabase INSTANCE;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(4);

    public abstract QrCodeDao qrCodeDao();

    public static QrCodeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (QrCodeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            QrCodeDatabase.class, "qrcode_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
