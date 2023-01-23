package com.example.aplikasigamesuit.game

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.aplikasigamesuit.R
import com.example.aplikasigamesuit.utils.Constants
import com.example.aplikasigamesuit.utils.Constants.KEY_NAME
import com.example.aplikasigamesuit.utils.Constants.KEY_PLAYER_TWO
import com.example.aplikasigamesuit.utils.Constants.PLAYER_CPU
import com.example.aplikasigamesuit.databinding.ActivityGameBinding


class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    private lateinit var username: String
    private lateinit var playerTwo: String

    private var playerInput: String = ""
    private var playerTwoInput: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra(KEY_NAME).toString()
        playerTwo = intent.getStringExtra(KEY_PLAYER_TWO).toString()

        setResource()
        startGame()
    }

    private fun setResource() {
        binding.tvPlayerOne.text = username
        binding.tvPlayerTwo.text = playerTwo
    }

    private fun startGame() {
        setPlayerOneInputListener()
        setListeners()
    }

    private fun setPlayerOneInputListener() {
        binding.rgInputPlayer.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.rb_batu_player -> {
                    playerInput = INPUT_BATU
                    Log.i(TAG, "$username meng-input = $playerInput")
                    showLogLayout(username, playerInput)

                    if (playerTwo == PLAYER_CPU) {
                        setComputer()
                    } else {
                        setPlayerTwoInputListener()
                    }

                }
                R.id.rb_kertas_player -> {
                    playerInput = INPUT_KERTAS
                    Log.i(TAG, "$username meng-input = $playerInput")
                    showLogLayout(username, playerInput)

                    if (playerTwo == PLAYER_CPU) {
                        setComputer()
                    } else {
                        setPlayerTwoInputListener()
                    }
                }
                R.id.rb_gunting_player -> {
                    playerInput = INPUT_GUNTING
                    Log.i(TAG, "$username meng-input = $playerInput")
                    showLogLayout(username, playerInput)

                    if (playerTwo == PLAYER_CPU) {
                        setComputer()
                    } else {
                        setPlayerTwoInputListener()
                    }
                }
            }

            for (i in 0 until radioGroup.childCount) {
                radioGroup.getChildAt(i).isClickable = false
            }
        }
    }

    private fun setPlayerTwoInputListener() {
        for (i in 0 until binding.rgInputPlayerTwo.childCount) {
            binding.rgInputPlayerTwo.getChildAt(i).isClickable = true
        }

        binding.rgInputPlayerTwo.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.rb_batu_player_two -> {
                    playerTwoInput = INPUT_BATU
                    Log.i(TAG, "$playerTwo meng-input = $playerTwoInput")
                    showLogLayout(playerTwo, playerTwoInput)
                    getResult()
                }
                R.id.rb_kertas_player_two -> {
                    playerTwoInput = INPUT_KERTAS
                    Log.i(TAG, "$playerTwo meng-input = $playerTwoInput")
                    showLogLayout(playerTwo, playerTwoInput)
                    getResult()
                }
                R.id.rb_gunting_player_two -> {
                    playerTwoInput = INPUT_GUNTING
                    Log.i(TAG, "$playerTwo meng-input = $playerTwoInput")
                    showLogLayout(playerTwo, playerTwoInput)
                    getResult()
                }
            }

            for (i in 0 until radioGroup.childCount) {
                radioGroup.getChildAt(i).isClickable = false
            }
        }
    }

    private fun setComputer() {
        playerTwoInput = (arrayOf(INPUT_BATU, INPUT_GUNTING, INPUT_KERTAS)).random()
        when (playerTwoInput) {
            INPUT_BATU -> {
                binding.rgInputPlayerTwo.check(R.id.rb_batu_player_two)
                Log.i(TAG, "$playerTwo meng-input = $playerTwoInput")
                showLogLayout(playerTwo, playerTwoInput)
            }
            INPUT_KERTAS -> {
                binding.rgInputPlayerTwo.check(R.id.rb_kertas_player_two)
                Log.i(TAG, "$playerTwo meng-input = $playerTwoInput")
                showLogLayout(playerTwo, playerTwoInput)
            }
            INPUT_GUNTING -> {
                binding.rgInputPlayerTwo.check(R.id.rb_gunting_player_two)
                Log.i(TAG, "$playerTwo meng-input = $playerTwoInput")
                showLogLayout(playerTwo, playerTwoInput)
            }
        }
        getResult()
    }

    private fun getResult() {
        when (playerTwoInput) {
            playerInput -> {
                Log.i(TAG, "DRAW! Tidak ada Pemenang.")
                this.showDialog(this, "DRAW")
            }
            INPUT_BATU -> {
                if (playerInput == INPUT_KERTAS) {
                    Log.i(TAG, "Pemenang adalah = $username")
                    this.showDialog(this, username)
                } else {
                    Log.i(TAG, "Pemenang adalah = CPU")
                    this.showDialog(this, playerTwo)
                }
            }
            INPUT_KERTAS -> {
                if (playerInput == INPUT_GUNTING) {
                    Log.i(TAG, "Pemenang adalah =  $username")
                    this.showDialog(this, username)
                } else {
                    Log.i(TAG, "Pemenang adalah = CPU")
                    this.showDialog(this, playerTwo)
                }
            }
            INPUT_GUNTING -> {
                if (playerInput == INPUT_BATU) {
                    Log.i(TAG, "Pemenang adalah = $username")
                    this.showDialog(this, username)
                } else {
                    Log.i(TAG, "Pemenang adalah = COMPUTER")
                    this.showDialog(this, playerTwo)
                }
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            btnClose.setOnClickListener { onBackPressed() }
            btnRefresh.setOnClickListener {
                refreshGame()
            }
        }
    }

    private fun showDialog(context: Context, winner: String) {
        val dialog = Constants.showDialogInfo(context)
        val winnerText = dialog.findViewById<TextView>(R.id.tv_winner_info)
        val btnRestart = dialog.findViewById<Button>(R.id.btn_restart)
        val btnMenu = dialog.findViewById<Button>(R.id.btn_menu)

        if (winner == "DRAW") {
            winnerText.text = StringBuilder("$winner!")
        } else {
            winnerText.text = StringBuilder("$winner MENANG!")
        }

        btnRestart.setOnClickListener {
            refreshGame()
            dialog.dismiss()
        }

        btnMenu.setOnClickListener {
            finish()
        }

        dialog.show()
    }

    private fun showLogLayout(player: String, input: String) {
        binding.apply {
            tvInputLog.visibility = View.VISIBLE
            tvInputLog.text = StringBuilder("$player Memilih $input")
        }
    }

    private fun refreshGame() {
        playerInput = ""
        playerTwoInput = ""

        binding.apply {
            rgInputPlayer.setOnCheckedChangeListener(null)
            rgInputPlayerTwo.setOnCheckedChangeListener(null)
            rgInputPlayer.clearCheck()
            rgInputPlayerTwo.clearCheck()
            tvInputLog.visibility = View.GONE
        }

        setPlayerOneInputListener()

        Log.i(TAG, "CLEAR")

        for (i in 0 until binding.rgInputPlayer.childCount) {
            binding.rgInputPlayer.getChildAt(i).isClickable = true
        }
    }

    companion object {
        const val INPUT_BATU = "batu"
        const val INPUT_KERTAS = "kertas"
        const val INPUT_GUNTING = "gunting"

        const val TAG = "GameActivity"
    }
}