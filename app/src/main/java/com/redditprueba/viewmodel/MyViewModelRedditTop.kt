package com.redditprueba.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redditprueba.domain.model.comments.RedditPostResponse
import com.redditprueba.domain.model.post.RedditApiResponse
import com.redditprueba.domain.repository.RedditTopRepository
import com.redditprueba.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModelRedditTop(private val repository: RedditTopRepository):ViewModel(){




    private var _redditEntries : MutableLiveData<Resource<RedditApiResponse>> = MutableLiveData()
    var redditEntries : LiveData<Resource<RedditApiResponse>> = _redditEntries

    private var _redditComments : MutableLiveData<Resource<List<RedditPostResponse>>> = MutableLiveData()
    var redditComments : LiveData<Resource<List<RedditPostResponse>>> = _redditComments


    private var count = 10

    fun loadNextPage() {
        viewModelScope.launch(Dispatchers.IO) {



             val response = repository.getPublishing(limit = count, after = null)
             _redditEntries.postValue(response)


            }
      }


    fun getComments(subReddit:String,postId:String) {


        viewModelScope.launch(Dispatchers.IO) {

            val response = repository.getComment(subReddit,postId)
            _redditComments.postValue(response)

        }
    }


    fun updateCount(){
        this.count += 10

    }

   }
//}


//}




