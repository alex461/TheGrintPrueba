package com.redditprueba.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redditprueba.databinding.FragmentHomeBinding
import com.redditprueba.domain.model.toDomain
import com.redditprueba.domain.network.RedditApi
import com.redditprueba.domain.repository.RedditTopRepository
import com.redditprueba.utils.BaseFragment
import com.redditprueba.utils.Resource
import com.redditprueba.viewmodel.MyViewModelRedditTop
import com.redditprueba.utils.*
import com.redditprueba.utils.Constant.Companion.BASEURL
import com.redditprueba.view.adapter.AdapterPost


class HomeFragment : BaseFragment<MyViewModelRedditTop, FragmentHomeBinding, RedditTopRepository>() {

    private lateinit var adapter: AdapterPost


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){


            adapter = AdapterPost(listOf(), postClickListener ={

                val action = HomeFragmentDirections.actionHomeFragmentToPostFragment(it.data?.toDomain())
                findNavController().navigate(action)



            })

            val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            rvPosts.layoutManager = linearLayoutManager

            binding.rvPosts.adapter = adapter


            rvPosts.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItem >= totalItemCount && firstVisibleItem >= 0) {

                        viewModel.loadNextPage()
                    }
                }
            })


            viewModel.loadNextPage()


            viewModel.redditEntries.observe(viewLifecycleOwner){




                when(it){

                    is  Resource.Success -> {


                        val response = it.value.data?.children

                        response?.let { it1 -> adapter.updateList(it1) }
                        viewModel.updateCount()


                    }

                    is Resource.Loading -> {


                    }



                    is Resource.Failure -> handleApiError(it)


                }















            }



        }


    }


    override fun getViewModel()= MyViewModelRedditTop::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    )= FragmentHomeBinding.inflate(inflater,container,false)

    override fun getFragmentRepository(): RedditTopRepository {
        val api = remoteDataSource.buildApi(RedditApi::class.java,BASEURL)
        return RedditTopRepository(api)    }

}