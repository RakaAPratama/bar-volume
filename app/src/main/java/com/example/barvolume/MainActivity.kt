package com.example.barvolume

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edWidth: EditText
    private lateinit var edHeight: EditText
    private lateinit var edLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        edWidth = findViewById(R.id.edt_width)
        edHeight = findViewById(R.id.edt_height)
        edLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_calculate) {
            val inputLength = edLength.text.toString().trim()
            val inputWidth = edWidth.text.toString().trim()
            val inputHeight = edHeight.text.toString().trim()
            var isEmptyField = false

            if (inputLength.isEmpty()) {
                isEmptyField = true
                edLength.error = "Field panjang tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyField = true
                edWidth.error = "Field lebar tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyField = true
                edHeight.error = "Field tinggi tidak boleh kosong"
            }
            if (!isEmptyField) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }
}