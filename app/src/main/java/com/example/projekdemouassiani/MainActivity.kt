package com.example.projekdemouassiani

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var listWisata :ArrayList<ItemData>
    private lateinit var wisataAdapter: MyAdapter
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

        wisataRecyclerView = findViewById(R.id.wisatarv)
        listWisata = ArrayList()

        listWisata.add(ItemData(R.drawable.jembatanmerah, "Jembatan Merah"))
        listWisata.add(ItemData(R.drawable.bukitteletubis, "Bukit Teletubis"))
        listWisata.add(ItemData(R.drawable.danaulove, "Danau Love"))
        listWisata.add(ItemData(R.drawable.danausentani, "Danau Sentani"))
        listWisata.add(ItemData(R.drawable.jprcity, "Puncak Jayapura City"))
        listWisata.add(ItemData(R.drawable.pasirenam, "Pantai Pasir 6"))
        listWisata.add(ItemData(R.drawable.tablanusu, "Pantai Tablanusu"))
        listWisata.add(ItemData(R.drawable.telukyotefa, "Teluk Youtefa"))
        listWisata.add(ItemData(R.drawable.terjuncyclop, "Air Terjun Cycloop"))
        listWisata.add(ItemData(R.drawable.kalbir, "Kali Biru Genyem"))

        wisataRecyclerView.layoutManager = LinearLayoutManager(this)
        wisataRecyclerView.setHasFixedSize(true)
        wisataAdapter = MyAdapter(listWisata)
        wisataRecyclerView.adapter = wisataAdapter
    }
}