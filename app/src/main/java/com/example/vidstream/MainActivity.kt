package com.example.vidstream

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vidstream.databinding.ActivityMainBinding
import com.example.vidstream.networkUtils.ItemsData
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MyItemClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemsAdapter
    private lateinit var supabase: SupabaseClient
    private var listItems: List<ItemsData> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        adapter = ItemsAdapter(listItems, this, this)
        binding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rv.adapter = adapter



        // supabase initialization
        supabase = createSupabaseClient(
            supabaseUrl = "https://bbvarubfymsvzuckhexz.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJidmFydWJmeW1zdnp1Y2toZXh6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUxMzg2MzMsImV4cCI6MjAzMDcxNDYzM30.3YRvHJYkHb_BuAXk_1cjP_baOlIjCk3iwOgQtNk6OKE"
        ) {
            install(Postgrest)
            Log.d("connectionSuccess", "connected to supabase")
        }


        // Fetching data
        lifecycleScope.launch {
            listItems = supabase
                                            .from("items")
                                            .select()
                                            .decodeList()

            Toast.makeText(this@MainActivity, "data fetched!", Toast.LENGTH_LONG).show()
            Log.d("database", listItems.toString())
            adapter.refreshList(listItems)
        }


        // Searching
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.toString().isNullOrBlank()) {
                    adapter.refreshList(listItems)
                    return false
                }

                lifecycleScope.launch {
//                    val retrievedData = supabase
//                        .from("items")
//                        .select {
//                            filter {
////                                textSearch("title || ' ' || description || ' ' || channel", "'${query.toString()}'", TextSearchType.NONE)
//                                textSearch("search_throughout", "$query", TextSearchType.NONE)
//                            }
//                        }

//                    retrievedData.data.map {
//                        Log.d("supa data", it.toString())
//                    }

//                    Log.d("new data supa", retrievedData.data)
//                    retrievedData.data.forEach {
//                        Log.d("new data", it.toString())
//                    }

//                    val newList = parseJsonToItemsList(retrievedData.data)
//                    Log.d("new data list", newList.toString())

//                    Log.d("search query", retrievedData.toString())

                    val newList = listItems.filter {
                        it.title.contains(query.toString(), ignoreCase = true) ||
                        it.description.contains(query.toString(), ignoreCase = true) ||
                        it.channel.contains(query.toString(), ignoreCase = true)
                    }
                    adapter.refreshList(newList)

                }
                return false
            }

        })
    }

    override fun onItemClicked(item: ItemsData) {
        Intent(this, VideoActivity::class.java).also {
            it.putExtra("data", item)
            startActivity(it)
        }
    }
}