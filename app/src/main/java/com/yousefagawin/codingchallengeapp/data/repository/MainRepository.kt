package com.yousefagawin.codingchallengeapp.data.repository

import com.yousefagawin.codingchallengeapp.data.db.AppDatabase
import com.yousefagawin.codingchallengeapp.datamodels.SongEntity
import com.yousefagawin.codingchallengeapp.data.network.MyApiInterface
import com.yousefagawin.codingchallengeapp.datamodels.ITunesResponse
import retrofit2.Response

class MainRepository (
    private val api: MyApiInterface,
    private val db: AppDatabase
){
    //this function will now return an AuthResponse directly because of the SafeApiRequest
    suspend fun getFromITunes(): Response<ITunesResponse> {

        return api.getFromITunes()
    }

    //this function will return the songs from the database
    fun getAllSongs() = db.songDao().getAll()
    suspend fun saveSongs(songEntity: SongEntity) = db.songDao().insertAll(songEntity)


}