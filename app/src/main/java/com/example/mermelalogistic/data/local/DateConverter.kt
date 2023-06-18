package com.example.mermelalogistic.data.local

import androidx.room.TypeConverter
import java.sql.Date

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}