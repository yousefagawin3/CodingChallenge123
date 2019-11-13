package com.yousefagawin.codingchallengeapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yousefagawin.codingchallengeapp.R
import com.yousefagawin.codingchallengeapp.adapter.SongsRVAdapter
import com.yousefagawin.codingchallengeapp.datamodels.SongEntity
import com.yousefagawin.codingchallengeapp.ui.onclick.OnClickActivity
import com.yousefagawin.codingchallengeapp.util.hide
import com.yousefagawin.codingchallengeapp.util.show
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainActivityListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainActivityViewModel

    private var recyclerLayoutManager: RecyclerView.LayoutManager? =null
    private var mAdapter: SongsRVAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)   //configure dagger
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        viewModel.mainActivityListener = this

        //initialization for transactions_rv
        this.transactions_rv?.hasFixedSize()
        recyclerLayoutManager = LinearLayoutManager(this)
        this.transactions_rv.layoutManager = recyclerLayoutManager


        //this is where the previous time of user will be saved
        val mSharedPreferences = getSharedPreferences("myTimePref", MODE_PRIVATE)
        if(mSharedPreferences!!.getString("time", null) == null){
            viewModel.getFromItunes()

        }
        else{
            getSongs()
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formattedDate = current.format(formatter)

        val editor = mSharedPreferences.edit()
        editor.putString("time", formattedDate)
        editor.apply()


        //this will update the date
        date_tv.text = "Last Opened: " + mSharedPreferences!!.getString("time", null)
    }

    //this will observe the songs Saved in the DB
    private fun getSongs(){
        //this will listen on the database if there are saved data
        viewModel.getAllSongs().observe(this, Observer {

            this.transactions_rv.adapter = SongsRVAdapter(this, it as ArrayList<SongEntity>)
            mAdapter = transactions_rv.adapter as SongsRVAdapter
            mAdapter!!.setOnItemClickListener(object : SongsRVAdapter.OnItemClickListener {
                override fun onItemClick(songEntity: SongEntity?) {
                    val intent = Intent (this@MainActivity, OnClickActivity::class.java)
                    intent.putExtra("artworkUrl100", songEntity!!.artworkUrl100)
                    intent.putExtra("trackName", songEntity.trackName)
                    intent.putExtra("trackPrice", songEntity.trackPrice)
                    intent.putExtra("genre", songEntity.primaryGenreName)
                    intent.putExtra("longDesc", songEntity.longDescription)
                    this@MainActivity.startActivity(intent)
                }
            })
        })
    }


    override fun onStarted() {
        Log.e("started", "started")

        loading_pb.show()


    }

    override fun onSuccess(songsArray: ArrayList<SongEntity>) {


        this.transactions_rv.adapter = SongsRVAdapter(this, songsArray)
        mAdapter = transactions_rv.adapter as SongsRVAdapter


        loading_pb.hide()
    }

    override fun onFailure(message: String) {


        error_tv.text = message
        loading_pb.hide()

    }
}
