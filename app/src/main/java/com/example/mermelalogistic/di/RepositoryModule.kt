package com.example.mermelalogistic.di

import com.example.mermelalogistic.data.local.home.repository.FactoryRepository
import com.example.mermelalogistic.data.local.home.repository.FactoryRepositoryDataSource
import com.example.mermelalogistic.data.local.login.repository.LoginRepository
import com.example.mermelalogistic.data.local.login.repository.LoginRepositoryDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(loginRepository: LoginRepository) : LoginRepositoryDataSource


    @Binds
    abstract fun bindFactoryRepository(factoryRepository: FactoryRepository) : FactoryRepositoryDataSource
}