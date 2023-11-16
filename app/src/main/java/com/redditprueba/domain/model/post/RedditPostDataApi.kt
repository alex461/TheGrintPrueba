package com.redditprueba.domain.model.post

data class RedditPostDataApi(
    val id:String,
    val title: String?,
    val author: String?,
    val name:String?,
    val subreddit:String?,
    val subreddit_name_prefixed:String?,
    val domain:String,
    val score:Long?,
    val num_comments:Long?,
    val thumbnail:String?,
    val created_utc: Long?,
)