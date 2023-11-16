package com.redditprueba.domain.model

import com.redditprueba.domain.model.post.RedditPostData
import com.redditprueba.domain.model.post.RedditPostDataApi


fun RedditPostDataApi.toDomain(): RedditPostData = RedditPostData(id, title, author, name,subreddit ,subreddit_name_prefixed,domain,score, num_comments, thumbnail, created_utc)