package com.yousefagawin.codingchallengeapp.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yousefagawin.codingchallengeapp.datamodels.SongEntity
import com.yousefagawin.codingchallengeapp.data.repository.MainRepository
import com.yousefagawin.codingchallengeapp.util.Coroutines
import com.yousefagawin.codingchallengeapp.util.NoInternetException
import org.json.JSONObject
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel(){

    var mainActivityListener: MainActivityListener? = null

    //this will observe if there are songs in the local database
    fun getAllSongs() = mainRepository.getAllSongs()

    fun getFromItunes(){
        mainActivityListener?.onStarted()

        //the retrofit call runs on the main thread
        Coroutines.main {
            //this try-catch is used to check if there is no internet during the initial launch of the app
            try{

                val response = mainRepository.getFromITunes()

                if(response.isSuccessful){

                    Log.e("content", response.body()!!.results[0].trackName)

                    val responseBody = response.body()

                        Log.e("saving in ", "local")
                        responseBody!!.results.forEach { songEntity ->
                            mainRepository.saveSongs(songEntity)
                        }


                    mainActivityListener!!.onSuccess(responseBody!!.results as ArrayList<SongEntity>)

                }
                else{

                    val errorBody = response.errorBody()!!.string()
                    Log.e("errorBody", errorBody)
                    val errorMessage = JSONObject(errorBody).getString("message")

                    mainActivityListener?.onFailure("Error ${response.code()}: ${errorMessage}")
                }

            }catch (e: NoInternetException){
                mainActivityListener?.onFailure(e.message!!)
            }
        }
    }





}