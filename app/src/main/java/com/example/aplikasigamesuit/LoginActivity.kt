package com.example.aplikasigamesuit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.binar.gamedesignbinarcp6.database.Users
import com.binar.gamedesignbinarcp6.database.UsersRoomDatabase
import com.binar.gamedesignbinarcp6.mvp.MainPresenterImpl
import com.binar.gamedesignbinarcp6.mvp.MainView
import com.example.aplikasigamesuit.databinding.ActivityLoginBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class LoginActivity : AppCompatActivity(), MainView {

    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var binding: ActivityLoginBinding
    var mDB: UsersRoomDatabase? = null
    private lateinit var data: List<Users>
    private var backToast: Toast? = null
    private var backPress: Long = 0

    private lateinit var loginPref: LoginPref
    private lateinit var mainPresenterImpl: MainPresenterImpl


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginPref = LoginPref(this)
        mainPresenterImpl = MainPresenterImpl(this, this)
        mDB = UsersRoomDatabase.builDataBase(this)

        GlobalScope.async {
            data = mDB?.UsersDao()?.getAllUsers()!!
            binding.btnLogin.setOnClickListener {
                var nameInDb: String? = null
                var numberInDb: String? = null
                val inputName = binding.edtUsername.text.toString()
                val inputNumber = binding.edtNohp.text.toString()

                if (inputName.isNotEmpty() && inputNumber.isNotEmpty()) {
                    runOnUiThread {
                        for (mData in data) {
                            if (inputName == mData.name) nameInDb = mData.name
                            if (inputNumber == mData.number) numberInDb = mData.number
                        }
                        if (nameInDb != null && numberInDb != null) {
                            loginPref.setLoginPref()
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            //intent.putExtra(MainActivity.KEY_NAME, inputName)
                            Log.i(TAG, "Kirim Nama $inputName")
                            startActivity(intent)
                            //startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "Harap Membuat Akun Terlebih Dahulu!!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Lengkapi Form", Toast.LENGTH_SHORT).show()
                }

            }
            binding.tvDontAlreadyAccount.setOnClickListener {
                startActivity(Intent(applicationContext, RegisterActivty::class.java))
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        backToast = Toast.makeText(this, "Press back again to exit!", Toast.LENGTH_SHORT)
        if (backPress + 2000 > System.currentTimeMillis()) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            backToast?.cancel()
            return super.onKeyDown(keyCode, event)
        } else {
            backToast?.show()
        }
        backPress = System.currentTimeMillis()
        return true
    }

    override fun onGetUsers(users: List<Users>) {
    }
}