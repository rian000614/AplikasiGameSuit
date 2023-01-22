package com.example.aplikasigamesuit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.binar.gamedesignbinarcp6.database.Users
import com.binar.gamedesignbinarcp6.database.UsersRoomDatabase
import com.binar.gamedesignbinarcp6.mvp.MainPresenterImpl
import com.binar.gamedesignbinarcp6.mvp.MainView
import com.example.aplikasigamesuit.databinding.ActivityRegisterBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class RegisterActivty : AppCompatActivity() , MainView {

    private lateinit var binding: ActivityRegisterBinding
    var mDB: UsersRoomDatabase? = null
    private lateinit var mainPresenterImpl: MainPresenterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mDB = UsersRoomDatabase.builDataBase(this)
        mainPresenterImpl = MainPresenterImpl(this, this)


        binding.btnSignUp.setOnClickListener {

            val name = binding.edtUsername.text.toString()
            val email = binding.edtEmail.text.toString()
            val number = binding.edtNohp.text.toString()
            val users = Users(null, name, email, number)

            if (name.isNotEmpty() && email.isNotEmpty() && number.isNotEmpty()) {
                GlobalScope.async {
                    mainPresenterImpl.addUser(users)
                    runOnUiThread {
                        Toast.makeText(this@RegisterActivty, "Data Berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@RegisterActivty, LoginActivity::class.java))
                    }
                }
            } else{
                Toast.makeText(this@RegisterActivty, "Lengkapi Form", Toast.LENGTH_SHORT).show()
            }
        }
        binding.tvAlreadyAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun onGetUsers(users: List<Users>) {
    }

}