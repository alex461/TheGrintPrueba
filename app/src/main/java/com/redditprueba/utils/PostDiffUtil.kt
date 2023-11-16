package com.redditprueba.utils

import androidx.recyclerview.widget.DiffUtil
import com.redditprueba.domain.model.post.RedditPost

class PostDiffUtil(
    private val oldList:List<RedditPost>,
    private val newList:List<RedditPost>
): DiffUtil.Callback(){


    override fun getOldListSize()=oldList.size

    override fun getNewListSize()=newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].data?.id == newList[newItemPosition].data?.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition]== newList[newItemPosition]
    }
}