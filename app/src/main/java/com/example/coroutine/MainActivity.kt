package com.example.coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://www.gstatic.com/webp/gallery/1.jpg
        val myUrl = URL("https://www.marlerblog.com/files/2013/03/orange.jpg")
        GlobalScope.launch(Dispatchers.Main) {
            val serverResp = getImg(myUrl.toString())
            showRes(serverResp)

        }
        }

    private suspend fun getImg(url: String): Bitmap =
        withContext(Dispatchers.IO){
           return@withContext BitmapFactory.decodeStream(URL(url).openStream())
        }


    private fun showRes(bitmap: Bitmap){
        bitmap?.run {
            imageView.setImageBitmap(bitmap)
        }
    }
    }
