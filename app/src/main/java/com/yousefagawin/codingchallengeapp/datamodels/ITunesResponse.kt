package com.yousefagawin.codingchallengeapp.datamodels


data class ITunesResponse(
    val resultCount: Int,
    val results: List<SongEntity>
)