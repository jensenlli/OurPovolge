package com.example.narodi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.asLiveData
import com.example.narodi.databinding.ActivityContentBinding
import com.example.narodi.db.Item
import com.example.narodi.db.MainDb

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityContentBinding.inflate(layoutInflater)

        setContentView(binding.root)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val item = intent.getSerializableExtra("item") as ItemNarod
        binding.apply {
            imNar.setImageResource(item.avatarUrl)
            tvTitleCon.text = item.nameN
            tvMainCon.setMovementMethod(ScrollingMovementMethod())
            tvMainCon.text = item.description
            if (item.favorite == 0){
                imFavCon.setImageResource(R.drawable.fav_inact_cont)
                imageView8.setImageResource(R.drawable.fav_button)
            }
            else{
                imFavCon.setImageResource(R.drawable.fav_act_cont)
                imageView8.setImageResource(R.drawable.fav_button_in)

            }
        }
    }

   fun onClickFavorite(view: View?){
       val db = MainDb.getDb(this)
       val item = intent.getSerializableExtra("item") as ItemNarod
       binding.apply {
           val itemp = Item(item.id,item.nameN,item.description,item.avatarUrl, 1)
           val itemm = Item(item.id,item.nameN,item.description,item.avatarUrl, 0)
           if (item.favorite == 0){
                db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                    Thread{
                        db.getDao().insertItem(itemp)
                    }.start()
                    imFavCon.setImageResource(R.drawable.fav_act_cont)
                    imageView8.setImageResource(R.drawable.fav_button_in)

                }
           }
           else{
               db.getDao().getAllItems().asLiveData().observe(this@ContentActivity){
                   Thread{
                       db.getDao().insertItem(itemm)
                   }.start()
                   imFavCon.setImageResource(R.drawable.fav_inact_cont)
                   imageView8.setImageResource(R.drawable.fav_button)

               }
           }
       }


   }

    fun onClickToMenu(view: View?) {
        val ToMenuIntent = Intent(this, MainActivity::class.java)
        startActivity(ToMenuIntent)
        finish()
    }
}