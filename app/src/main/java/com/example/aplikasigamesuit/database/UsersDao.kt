package com.binar.gamedesignbinarcp6.database

import androidx.room.*

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(users: Users)

    @Query("SELECT * FROM users")
    fun getAllUsers():List<Users>

    @Query("SELECT * FROM users WHERE :id = id")
    fun getUsersById(id: Int): Users
    @Update
    fun update(users: Users)
    @Delete
    fun delete(users: Users)
}