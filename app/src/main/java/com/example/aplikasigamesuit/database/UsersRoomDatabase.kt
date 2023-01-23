package com.binar.gamedesignbinarcp6.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class UsersRoomDatabase: RoomDatabase() {
    abstract fun UsersDao(): UsersDao

    companion object {
        private var INSTANCE: UsersRoomDatabase? = null

        fun builDataBase(context: Context): UsersRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(UsersRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UsersRoomDatabase::class.java, "Users.db").build()
                }
            }
            return INSTANCE
        }

        fun getIntance(context: Context): UsersRoomDatabase? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: builDataBase(context).also { INSTANCE = it }
            }
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}