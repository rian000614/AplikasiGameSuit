package com.example.aplikasigamesuit.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasigamesuit.utils.Constants.KEY_NAME
import com.example.aplikasigamesuit.utils.Constants.KEY_PLAYER_TWO
import com.example.aplikasigamesuit.utils.Constants.PLAYER_CPU
import com.example.aplikasigamesuit.utils.Constants.PLAYER_TWO
import com.example.aplikasigamesuit.databinding.ActivityMainBinding
import com.google.androidgamesdk.GameActivity

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