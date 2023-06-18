package com.example.mermelalogistic.data.local.login.repository

import com.example.mermelalogistic.data.local.entities.Factory

interface LoginRepositoryDataSource {
    suspend fun login(factoryId : String,password : String,factoryType : String) : Factory?
}