package com.example.aplikasigamesuit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplikasigamesuit.databinding.ActivityGamesGuideBinding

class GamesGuideActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGamesGuideBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_games_guide)

        binding = ActivityGamesGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
          //  startActivity(Intent(this, ))
        }
    }
}