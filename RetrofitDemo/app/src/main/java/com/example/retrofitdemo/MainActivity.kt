package com.example.retrofitdemo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofitService: AlbumsService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        retrofitService = RetrofitInstance.getRetrofitInstance().create(AlbumsService::class.java)

        getAlbums()
        getAlbumById()
    }

    private fun getAlbums() {
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retrofitService.getAlbums()
//            val response = retrofitService.getAlbumsByUserId(3)
            emit(response)
        }

        responseLiveData.observe(this) {
            val albumsList = it.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val albumsItemString = "Albums ID: ${albumsItem.id} \n" +
                            "Albums User ID: ${albumsItem.userId} \n" +
                            "Albums Title: ${albumsItem.title} \n\n"

                    binding.albumsTextView.append(albumsItemString)
                }

                addAlbum()
            }
        }
    }

    private fun getAlbumById() {
        val pathResponseLiveData: LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitService.getAlbumById(5)
            emit(response)
        }

        pathResponseLiveData.observe(this) {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        }
    }

    private fun addAlbum() {
        val album = AlbumsItem(0, "New album", 5)
        val postResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retrofitService.addAlbum(album)
            emit(response)
        }

        postResponse.observe(this) {
            val receivedAlbumsItem = it.body()

            if (receivedAlbumsItem != null) {
                val albumsItemString = "Albums ID: ${receivedAlbumsItem.id} \n" +
                        "Albums User ID: ${receivedAlbumsItem.userId} \n" +
                        "Albums Title: ${receivedAlbumsItem.title} \n\n"

                binding.albumsTextView.append(albumsItemString)
            }
        }
    }
}