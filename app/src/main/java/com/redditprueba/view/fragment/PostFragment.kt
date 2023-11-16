package com.redditprueba.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.redditprueba.R
import com.redditprueba.databinding.FragmentPostBinding
import com.redditprueba.domain.network.RedditApi
import com.redditprueba.domain.repository.RedditTopRepository
import com.redditprueba.utils.*
import com.redditprueba.view.adapter.AdapterComments
import com.redditprueba.viewmodel.MyViewModelRedditTop


class PostFragment :  BaseFragment<MyViewModelRedditTop, FragmentPostBinding, RedditTopRepository>() {

    private val dataPost : PostFragmentArgs by navArgs()
    private lateinit var adapter: AdapterComments



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        with(binding){




            adapter = AdapterComments(listOf())

            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false).also { rvComments.layoutManager = it }

            binding.rvComments.adapter = adapter


            tvSubreddit.text = dataPost.post?.subreddit_name_prefixed

            tvTittle.text = dataPost.post?.title

            tvHour.text = "  4 Hour"




            GlideApp.with(thumbnailImageView.context)
                .load(stripThumbnailParams(dataPost.post?.thumbnail))
                .fitCenter()
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(thumbnailImageView)




            scoreTextView.text = String.format( " ${dataPost.post?.score ?: 0L }")

            commentsTextView.text = String.format( " ${dataPost.post?.num_comments ?: 0L } Comments")


            viewModel.getComments(dataPost.post?.subreddit ?: "",dataPost.post?.id ?: "")


            viewModel.redditComments.observe(viewLifecycleOwner){

                when(it){

                    is  Resource.Success -> {


                        val response = it.value.get(1).data?.children

                        response?.let { it1 -> adapter.updateList(it1) }


                    }

                    is Resource.Loading -> {


                    }



                    is Resource.Failure -> handleApiError(it)


                }



            }



        }


    }


    private fun stripThumbnailParams(thumbnailUrl: String?): String {
        return thumbnailUrl?.split("?")?.get(0) ?: ""
    }



    override fun getViewModel()= MyViewModelRedditTop::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentPostBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): RedditTopRepository {
        val api = remoteDataSource.buildApi(RedditApi::class.java, Constant.BASEURL)
        return RedditTopRepository(api)    }

}