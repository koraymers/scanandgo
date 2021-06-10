package com.example.k_app.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "qrcodes")
public class QrCodeModel {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "code")
    public String code;
}
