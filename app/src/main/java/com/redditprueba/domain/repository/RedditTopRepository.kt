package com.redditprueba.domain.repository

import com.redditprueba.domain.network.RedditApi

class RedditTopRepository(private val api : RedditApi): BaseRepository() {

    suspend fun getPublishing(limit:Int, after: String?)=safeApiCall {

        api.getTopEntries(limit,after)
    }

    suspend fun getComment(subReddit:String,postId:String)=safeApiCall {



        api.getComments(subReddit,postId)
    }


}