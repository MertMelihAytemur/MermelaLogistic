package com.example.mermelalogistic.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mermelalogistic.data.local.dao.LogisticDao
import com.example.mermelalogistic.data.local.entities.*

@Database(
    entities = [Factory::class,
        Product::class,
        Request::class,
        Stock::class,
        Warehouse::class],
    version = 13,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class LogisticDatabase : RoomDatabase() {
    abstract fun logisticDao(): LogisticDao
}