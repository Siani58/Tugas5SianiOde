package com.example.projekdemouassiani

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val gambar : ImageView = findViewById(R.id.ivdetailimage)
        val nama : TextView = findViewById(R.id.tvdetailjudul)
        val biografi : TextView = findViewById(R.id.tvdescdetail)

        val bundle: Bundle?= intent.extras
        val bNama = bundle!!.getString("idnama")
        val bGambar = bundle.getInt("idgambar")
        val bKeterangan = bundle.getString("idketerangan")

        gambar.setImageResource(bGambar)
        nama.text = bNama
        biografi.text = bKeterangan
    }
}