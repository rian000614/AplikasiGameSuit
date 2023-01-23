package com.binar.gamedesignbinarcp6.mvp

import com.binar.gamedesignbinarcp6.database.Users

interface MainPresenter {
    suspend fun getUser()
    suspend fun addUser(users: Users)
}