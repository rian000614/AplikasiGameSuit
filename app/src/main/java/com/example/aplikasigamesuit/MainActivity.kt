package com.example.aplikasigamesuit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.aplikasigamesuit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var loginPref: LoginPref

    companion object {
        const val KEY_NAME = "key_name"
        const val KEY_NAME_FROM_DIALOG = "key_name_from_dialog"
        const val KEY_NAME_FROM_MAIN = "key_name_from_main"
        const val TAG = "MenuActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loginPref = LoginPref(this)

        var btn : Button = findViewById(R.id.btn)
        btn.setOnClickListener {
            val alertDialog : AlertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("Exit")
            alertDialog.setMessage("Do you want Exit ?")

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") {
                    dialog, which -> finish()
                dialog.dismiss()}

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No") {
                    dialog, which ->
                dialog.dismiss()

            }

            alertDialog.show()

//
        }










    }

        }
    }
