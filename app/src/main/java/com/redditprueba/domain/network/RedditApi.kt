package com.redditprueba.domain.network
import com.redditprueba.domain.model.comments.RedditPostResponse
import com.redditprueba.domain.model.post.RedditApiResponse
import retrofit2.http.*


interface RedditApi {

        @GET("/top.json")
        suspend fun getTopEntries(
            @Query("limit") limit: Int,
            @Query("after") after: String?,
        ): RedditApiResponse


    @GET("/r/{subreddit}/comments/{postId}.json")
    suspend fun getComments(
        @Path("subreddit") subreddit: String,
        @Path("postId") postId: String
    ): List<RedditPostResponse>

}