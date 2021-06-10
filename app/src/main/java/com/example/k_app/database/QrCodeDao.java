package com.example.k_app.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QrCodeDao {
    @Query("SELECT * FROM qrcodes")
    List<QrCodeModel> getAll();

    @Insert
    void insert(QrCodeModel model);

    @Delete
    void delete(QrCodeModel model);
}
