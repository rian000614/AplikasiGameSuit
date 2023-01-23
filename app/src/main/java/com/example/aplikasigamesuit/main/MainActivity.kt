package com.example.aplikasigamesuit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasigamesuit.utils.Constants.KEY_NAME
import com.example.aplikasigamesuit.utils.Constants.KEY_PLAYER_TWO
import com.example.aplikasigamesuit.utils.Constants.PLAYER_CPU
import com.example.aplikasigamesuit.utils.Constants.PLAYER_TWO
import com.example.aplikasigamesuit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var username: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        username = intent.getStringExtra(KEY_NAME).toString()

        setResources()
        setListeners()

        setContentView(binding.root)

        var btn: Button = findViewById(R.id.btn)
        btn.setOnClickListener {
            val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle("Exit")
            alertDialog.setMessage("Do you want Exit ?")

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes") { dialog, which ->
                finish()
                dialog.dismiss()
            }

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No") { dialog, which ->
                dialog.dismiss()

            }

            alertDialog.show()


        }


    }


private fun setResources() {
    binding.apply {
        tvPlayerVsPlayer.text = StringBuilder("$username vs Pemain")
        tvPlayerVsCom.text = StringBuilder("$username vs CPU")
        tvWelcomePlayer.text = StringBuilder("Selamat Datang $username")
    }
}

private fun setListeners() {
    binding.apply {
        layoutPlayerVsPlayer.setOnClickListener {
            val iGame = Intent(this@MainActivity, GameActivity::class.java)
            iGame.putExtra(KEY_NAME, username)
            iGame.putExtra(KEY_PLAYER_TWO, PLAYER_TWO)
            startActivity(iGame)
        }

        layoutPlayerVsCom.setOnClickListener {
            val iGame = Intent(this@MainActivity, GameActivity::class.java)
            iGame.putExtra(KEY_NAME, username)
            iGame.putExtra(KEY_PLAYER_TWO, PLAYER_CPU)
            startActivity(iGame)
        }

        btnCloseLayout.setOnClickListener {
            layoutWelcome.visibility = View.GONE
        }
    }
}
}