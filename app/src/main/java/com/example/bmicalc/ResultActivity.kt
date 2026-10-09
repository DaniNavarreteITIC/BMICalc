package com.example.bmicalc

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.resultatmain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvCategoria = findViewById<TextView>(R.id.categoria)
        val tvValor = findViewById<TextView>(R.id.valor)
        val tvDescripcion = findViewById<TextView>(R.id.tag_description)
        val btnRecalculate = findViewById<Button>(R.id.btnRecalculate)
        val btnTornar = findViewById<Button>(R.id.botoRes)
        val isMale = intent.getBooleanExtra("EXTRA_GENDER", true)
        val age = intent.getIntExtra("EXTRA_AGE", 18)

        val bmiResult = intent.getFloatExtra("Extra_BMI", 0.0f)

        tvValor.text = String.format(java.util.Locale.US, "%.2f", bmiResult)

        if (age < 19) {
            when {
                bmiResult < 15.0f -> {
                    tvCategoria.text = getString(R.string.underweight)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.blue))
                    tvDescripcion.text = if (isMale) "As a $age-year-old boy, this weight is quite low." else "As a $age-year-old girl, this weight is quite low."
                }
                bmiResult in 15.0f..21.9f -> {
                    tvCategoria.text = getString(R.string.normal)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.green))
                    tvDescripcion.text = getString(R.string.ideal18)
                }
                else -> {
                    tvCategoria.text = getString(R.string.overweight)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.red))
                    tvDescripcion.text = getString(R.string.careful18, age)
                }
            }
        } else {
            when {
                bmiResult < 18.5f -> {
                    tvCategoria.text = getString(R.string.underweight)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.blue))
                    tvDescripcion.text = getString(R.string.undermsg)
                }
                bmiResult in 18.5f..24.9f -> {
                    tvCategoria.text = getString(R.string.normal)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.green))
                    tvDescripcion.text = getString(R.string.normalmsg)
                }
                bmiResult in 25.0f..29.9f -> {
                    tvCategoria.text = getString(R.string.overweight)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.orange))
                    tvDescripcion.text = getString(R.string.overmsg)
                }
                else -> {
                    tvCategoria.text = getString(R.string.obese)
                    tvCategoria.setTextColor(ContextCompat.getColor(this, R.color.red))
                    tvDescripcion.text = getString(R.string.obesemsg)
                }
            }
        }
        btnRecalculate.setOnClickListener {
            finish()
        }
        btnTornar.setOnClickListener {
            finish()
        }

    }
}