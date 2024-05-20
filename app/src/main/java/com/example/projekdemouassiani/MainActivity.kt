package com.example.projekdemouassiani

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var nama : Array<String>
    private lateinit var gambar : Array<Int>
    private lateinit var keterangan : Array<String>
    private lateinit var listWisata :ArrayList<ItemData>
//    private lateinit var wisataAdapter: MyAdapter
    private lateinit var wisataRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gambar = arrayOf(
            R.drawable.bukitteletubis,
            R.drawable.danaulove,
            R.drawable.danausentani,
            R.drawable.jembatanmerah,
            R.drawable.jprcity,
            R.drawable.kalbir,
            R.drawable.pasirenam,
            R.drawable.tablanusu,
            R.drawable.telukyotefa,
            R.drawable.terjuncyclop,
        )

        nama = arrayOf(
            "Bukit Teletubis",
            "Danau Love",
            "Danau Sentani",
            "Jembatan Merah",
            "Puncak Jayapura City",
            "Kali Biru Genyem",
            "Pantai Pasir 6",
            "Pantai Tablanusu",
            "Teluk Youtefa",
            "Air Terjun Cycloop"
        )

        keterangan = arrayOf(
            getString(R.string.Bukit_Teletubis),
            getString(R.string.Danau_Love),
            getString(R.string.Danau_Sentani),
            getString(R.string.Jembatan_Merah),
            getString(R.string.Puncak_Jayapura_City),
            getString(R.string.Kali_Biru_Genyem),
            getString(R.string.Pantai_Pasir_6),
            getString(R.string.Pantai_Tablanusu),
            getString(R.string.Teluk_Youtefa),
            getString(R.string.Air_Terjun_Cycloop)
        )

//        wisataRecyclerView = findViewById(R.id.wisatarv)
//        listWisata = ArrayList()

//        listWisata.add(ItemData(R.drawable.jembatanmerah, "Jembatan Merah"))
//        listWisata.add(ItemData(R.drawable.bukitteletubis, "Bukit Teletubis"))
//        listWisata.add(ItemData(R.drawable.danaulove, "Danau Love"))
//        listWisata.add(ItemData(R.drawable.danausentani, "Danau Sentani"))
//        listWisata.add(ItemData(R.drawable.jprcity, "Puncak Jayapura City"))
//        listWisata.add(ItemData(R.drawable.pasirenam, "Pantai Pasir 6"))
//        listWisata.add(ItemData(R.drawable.tablanusu, "Pantai Tablanusu"))
//        listWisata.add(ItemData(R.drawable.telukyotefa, "Teluk Youtefa"))
//        listWisata.add(ItemData(R.drawable.terjuncyclop, "Air Terjun Cycloop"))
//        listWisata.add(ItemData(R.drawable.kalbir, "Kali Biru Genyem"))

        wisataRecyclerView = findViewById(R.id.wisatarv)

        wisataRecyclerView.layoutManager = LinearLayoutManager(this)
        wisataRecyclerView.setHasFixedSize(true)
//        wisataAdapter = MyAdapter(listWisata)
//        wisataRecyclerView.adapter = wisataAdapter

        listWisata = arrayListOf<ItemData>()
        getDataUser()
    }

    private fun getDataUser() {
        for (i in gambar.indices){
           val dataWisata = ItemData(gambar[i], nama[i])
            listWisata.add(dataWisata)
        }
        var adapter = MyAdapter(listWisata)
        wisataRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object: MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("idgambar",listWisata[position].gambar)
                intent.putExtra("idnama",listWisata[position].nama)
                intent.putExtra("idketerangan",keterangan[position])

                startActivity(intent)
            }
        })
    }
}