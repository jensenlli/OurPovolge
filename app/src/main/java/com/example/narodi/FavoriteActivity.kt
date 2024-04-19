package com.example.narodi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.narodi.databinding.ActivityFavoriteBinding
import com.example.narodi.db.MainDb


class FavoriteActivity : AppCompatActivity(), ItemNarodAdapter.Listener {

    lateinit var binding: ActivityFavoriteBinding
    private val adapter = ItemNarodAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            rcViewFav.layoutManager = GridLayoutManager(this@FavoriteActivity, 2)
            rcViewFav.adapter = adapter
            val db = MainDb.getDb(this@FavoriteActivity)
            db.getDao().getFavoriteItems().asLiveData().observe(this@FavoriteActivity) {
                it.forEach { it1 ->
                    val itemNarod = ItemNarod(
                        it1.id,
                        it1.nameN,
                        it1.description,
                        it1.avatarUrl,
                        it1.favorite
                    )

                    adapter.addItemNarod(itemNarod)

                    if (adapter.itemCount.equals(0)) {

                        textView5.visibility = View.VISIBLE
                    } else {

                        textView5.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    fun onClickToAboutUs(view: View?) {
        val ToAboutUsIntent = Intent(this, AboutUsActivity::class.java)
        startActivity(ToAboutUsIntent)
        finish()
    }

    fun onClickToMenu(view: View?) {
        val ToMenuIntent = Intent(this, MainActivity::class.java)
        startActivity(ToMenuIntent)
        finish()
    }

    fun onClickToFavorite(view: View?) {
    }

    override fun onClick(itemNarod: ItemNarod) {

        startActivity(Intent(this, ContentActivity::class.java).apply {
            putExtra("item", itemNarod)
        })
        finish()
    }
}