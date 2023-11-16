package com.redditprueba.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.redditprueba.databinding.CardCommentsBinding
import com.redditprueba.domain.model.comments.RedditPostChild
import com.redditprueba.utils.CommentsDiffUtil


class AdapterComments (
    private var commentsList: List<RedditPostChild>

): RecyclerView.Adapter<AdapterComments.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardCommentsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }


    fun updateList(newList: List<RedditPostChild>){
        val invoiceDiff = CommentsDiffUtil(commentsList,newList)
        val result = DiffUtil.calculateDiff(invoiceDiff)
        commentsList=newList
        result.dispatchUpdatesTo(this)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val post =commentsList[position]

        holder.bind(post)



    }

    override fun getItemCount()= commentsList.size



    class MyViewHolder (private val binding: CardCommentsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(comments: RedditPostChild) {

            with(binding) {

                tvUser.text =  comments.data?.author

                tvComments.text = comments.data?.body


            }
        }


    }

}


