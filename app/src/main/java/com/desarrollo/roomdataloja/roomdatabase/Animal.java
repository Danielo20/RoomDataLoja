package com.desarrollo.roomdataloja.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = Animal.TABLE_NAME)
public class Animal {
    public static final String TABLE_NAME = "animal";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "nombreAnimal")
    private String nombreAnimal;

    public Animal(@NonNull String nombreAnimal) {
        this.nombreAnimal = nombreAnimal;
    }

    public String getNombreAnimal() {
        return nombreAnimal;
    }
}
