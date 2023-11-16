package com.redditprueba.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.redditprueba.R
import com.redditprueba.databinding.CardPostsBinding
import com.redditprueba.domain.model.post.RedditPost
import com.redditprueba.utils.GlideApp
import com.redditprueba.utils.PostDiffUtil


class AdapterPost (
    private var postList: List<RedditPost>,
    private val postClickListener : (RedditPost)-> Unit

): RecyclerView.Adapter<AdapterPost.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CardPostsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ))
    }


    fun updateList(newList: List<RedditPost>){
        val invoiceDiff = PostDiffUtil(postList,newList)
        val result = DiffUtil.calculateDiff(invoiceDiff)
        postList=newList
        result.dispatchUpdatesTo(this)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val post =postList[position]

        holder.bind(post)

        holder.itemView.setOnClickListener {
            postClickListener(post)
        }


    }

    override fun getItemCount()= postList.size



    class MyViewHolder (private val binding: CardPostsBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(post: RedditPost) {

            with(binding) {

                tvSubreddit.text = post.data?.subreddit_name_prefixed ?: ""

                tvDomain.text = post.data?.domain ?: ""

                tvTittle.text = post.data?.title ?: ""

                tvAuthor.text = post.data?.author ?: ""


                scoreTextView.text = String.format( " ${post.data?.score ?: 0L }")

                commentsTextView.text = String.format( " ${post.data?.num_comments ?: 0L } Comments")

                GlideApp.with(thumbnailImageView.context)
                    .load(stripThumbnailParams(post.data?.thumbnail))
                    .fitCenter()
                    .placeholder(R.drawable.ic_no_image)
                    .error(R.drawable.ic_no_image)
                    .into(thumbnailImageView)

            }
        }

        private fun stripThumbnailParams(thumbnailUrl: String?): String {
            return thumbnailUrl?.split("?")?.get(0) ?: ""
        }

    }

}


