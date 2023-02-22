package com.binar.gamedesignbinarcp6.mvp

import com.binar.gamedesignbinarcp6.database.Users

interface MainView {
    fun onGetUsers(users: List<Users>)
}