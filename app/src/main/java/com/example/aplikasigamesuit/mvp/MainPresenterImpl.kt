package com.binar.gamedesignbinarcp6.mvp

import android.content.Context
import com.binar.gamedesignbinarcp6.database.Users
import com.binar.gamedesignbinarcp6.database.UsersDao
import com.binar.gamedesignbinarcp6.database.UsersRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainPresenterImpl(private val context: Context, private val mainView: MainView): MainPresenter {

    override suspend fun getUser() = withContext(Dispatchers.IO) {
        var usersRoomDatabase: UsersRoomDatabase? = null
        usersRoomDatabase = UsersRoomDatabase.builDataBase(context)
        val user = usersRoomDatabase?.UsersDao()?.getAllUsers()
        if (user != null) {
            mainView.onGetUsers(user)
        }
    }

    override suspend fun addUser(users: Users): Unit = withContext(Dispatchers.IO) {
        var usersRoomDatabase: UsersRoomDatabase? = null
        usersRoomDatabase = UsersRoomDatabase.builDataBase(context)
        usersRoomDatabase?.UsersDao()?.insert(users)
    }
}