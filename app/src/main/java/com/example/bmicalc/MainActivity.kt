package com.example.bmicalc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView

    private var isMaleSelected: Boolean = true

    private lateinit var tvAltura: TextView
    private lateinit var rsAltura: SeekBar
    private var currentWeight: Int = 170
    private var currentAge: Int = 18
    private var currentHeight: Int = 73
    private lateinit var tvPes: TextView
    private lateinit var tvEdat: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardMale = findViewById(R.id.home)
        cardFemale = findViewById(R.id.dona)

        tvAltura = findViewById(R.id.tvAltura)
        rsAltura = findViewById(R.id.rsAltura)

        tvPes = findViewById(R.id.tvPes)
        tvEdat = findViewById(R.id.tvEdat)

        tvPes.text = currentWeight.toString()
        tvEdat.text = currentAge.toString()
        tvAltura.text = currentHeight.toString()

        rsAltura.progress = currentHeight

        val btnMenysPes = findViewById<Button>(R.id.menysAltura)
        val btnMesPes = findViewById<Button>(R.id.mesAltura)
        val btnMenysEdat = findViewById<Button>(R.id.menysEdat)
        val btnMesEdat = findViewById<Button>(R.id.mesEdat)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)

        btnCalculate.setOnClickListener {
            val bmiResult = (currentWeight.toFloat() / (currentHeight *currentHeight)) *703
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("Extra_BMI", bmiResult)
            intent.putExtra("EXTRA_AGE", currentAge)
            intent.putExtra("EXTRA_GENDER", isMaleSelected)
            startActivity(intent)
        }

        btnMesPes.setOnClickListener {
            currentWeight++
            tvPes.text = currentWeight.toString()
        }
        btnMenysPes.setOnClickListener {
            currentWeight--
            tvPes.text = currentWeight.toString()
        }
        btnMesEdat.setOnClickListener {
            currentAge++
            tvEdat.text = currentAge.toString()
        }
        btnMenysEdat.setOnClickListener {
            currentAge--
            tvEdat.text = currentAge.toString()
        }

        setGenderColor()

        cardMale.setOnClickListener {
            isMaleSelected = true
            setGenderColor()
        }

        cardFemale.setOnClickListener {
            isMaleSelected = false
            setGenderColor()
        }
        rsAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvAltura.text = progress.toString()
                currentHeight = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

    }



    private fun setGenderColor() {
        if (isMaleSelected) {
            cardMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fonsCardsSelect))
            cardFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fonsCards))
        } else {
            cardFemale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fonsCardsSelect))
            cardMale.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fonsCards))
        }
    }

}