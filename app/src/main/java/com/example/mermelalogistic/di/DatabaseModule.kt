package com.example.mermelalogistic.di

import android.content.Context
import androidx.room.Room
import com.example.mermelalogistic.data.local.LogisticDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    //Hilt needs to know how to create an instance of NoteDatabase. For that add another method below provideDao.
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, LogisticDatabase::class.java, "logistic_db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
    //This annotation marks the method provideDao as a provider of noteDoa.
    @Provides
    fun provideDao(db: LogisticDatabase) = db.logisticDao()

}
