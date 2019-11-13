package com.yousefagawin.codingchallengeapp.ui.main

import com.yousefagawin.codingchallengeapp.datamodels.SongEntity

interface MainActivityListener {

    fun onStarted()
    fun onSuccess(songsArray: ArrayList<SongEntity>)
    fun onFailure(message: String)
}