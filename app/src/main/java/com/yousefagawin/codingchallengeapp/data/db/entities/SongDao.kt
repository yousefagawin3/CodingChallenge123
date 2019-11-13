package com.yousefagawin.codingchallengeapp.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yousefagawin.codingchallengeapp.datamodels.SongEntity

@Dao
interface SongDao {

    @Query("SELECT * FROM songentity")
    fun getAll(): LiveData<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: SongEntity)

    @Delete
    fun delete(songEntity: SongEntity)

    @Update
    fun updateSong(vararg songEntity: SongEntity)
}