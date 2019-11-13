package com.yousefagawin.codingchallengeapp.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongEntity(

    @PrimaryKey(autoGenerate = true)
    var uid: Int,

    @ColumnInfo(name = "artworkUrl100") var artworkUrl100: String?,
    @ColumnInfo(name = "trackName") var trackName: String?,
    @ColumnInfo(name = "trackPrice") var trackPrice: String?,
    @ColumnInfo(name = "primaryGenreName") var primaryGenreName: String?,
    @ColumnInfo(name = "longDescription") var longDescription: String?
)