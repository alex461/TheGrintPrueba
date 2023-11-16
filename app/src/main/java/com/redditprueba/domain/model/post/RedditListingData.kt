package com.redditprueba.domain.model.post

data class RedditListingData(
    val children: List<RedditPost>?,
    val after: String?
)