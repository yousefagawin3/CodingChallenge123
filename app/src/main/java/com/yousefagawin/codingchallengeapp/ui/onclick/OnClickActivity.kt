package com.yousefagawin.codingchallengeapp.ui.onclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yousefagawin.codingchallengeapp.R
import kotlinx.android.synthetic.main.activity_on_click.*

class OnClickActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_click)

        val bundle = getIntent().getExtras()


        Glide.with(this).load(bundle.getString("artworkUrl100"))
            //this will handle the issue if image is not loaded
            .apply(RequestOptions().placeholder(R.drawable.no_image_available).error(R.drawable.no_image_available))
            .into(image_iv)

        //this will hide the texts if some fields are null
        if(bundle.getString("trackName")== null){
            trackName_tv.visibility = View.INVISIBLE
        }
        else{
            trackName_tv.text = "Track Title: " + bundle.getString("trackName")
        }


        if(bundle.getString("trackPrice")== null){
            trackPrice_tv.visibility = View.INVISIBLE
        }
        else{
            trackPrice_tv.text = "Track Price: $" + bundle.getString("trackPrice")
        }


        if(bundle.getString("genre") == null){
            genre_tv.visibility = View.INVISIBLE
        }
        else{
            genre_tv.text = "Primary Genre: " + bundle.getString("genre")
        }

        if(bundle.getString("longDesc") == null){
            longDesc_tv.visibility = View.INVISIBLE
        }
        else{
            longDesc_tv.text = "Description: " + bundle.getString("longDesc")
        }

    }
}
