package com.example.vidstream

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vidstream.databinding.ActivityMainBinding
import com.example.vidstream.networkUtils.ItemsData
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import com.example.vidstream.BuildConfig
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class MainActivity : AppCompatActivity(), MyItemClickListener {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: ItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        var list: MutableList<ItemsData> = mutableListOf()

        adapter = ItemsAdapter(list, this, this)
        binding.rv.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rv.adapter = adapter



        // supabase initialization
        val supabase = createSupabaseClient(
            supabaseUrl = "https://bbvarubfymsvzuckhexz.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJidmFydWJmeW1zdnp1Y2toZXh6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTUxMzg2MzMsImV4cCI6MjAzMDcxNDYzM30.3YRvHJYkHb_BuAXk_1cjP_baOlIjCk3iwOgQtNk6OKE"
        ) {
            install(Postgrest)
            Log.d("connectionSuccess", "connected to supabase")
        }



        lifecycleScope.launch {
            var items: List<ItemsData> = supabase
                                            .from("items")
                                            .select()
                                            .decodeList()

            Toast.makeText(this@MainActivity, "data fetched!", Toast.LENGTH_LONG).show()
            Log.d("database", items.toString())
            adapter.refreshList(items)
        }
    }

    override fun onItemClicked(item: ItemsData) {
        Intent(this, VideoActivity::class.java).also {
            it.putExtra("data", item)
            startActivity(it)
        }
    }
}