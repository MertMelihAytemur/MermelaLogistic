package com.example.mermelalogistic.data.local.login.repository

import com.example.mermelalogistic.data.local.dao.LogisticDao
import com.example.mermelalogistic.data.local.entities.Factory
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDao: LogisticDao
    ) : LoginRepositoryDataSource {
    override suspend fun login(factoryId: String,password : String,factoryType : String): Factory? {
        return loginDao.getFactoryByCredentials(factoryId,password,factoryType)
    }

}