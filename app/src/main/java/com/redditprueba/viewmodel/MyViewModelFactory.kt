package com.redditprueba.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redditprueba.domain.repository.BaseRepository
import com.redditprueba.domain.repository.RedditTopRepository


class MyViewModelFactory(private val repositorio: BaseRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when{

            modelClass.isAssignableFrom(MyViewModelRedditTop::class.java) -> MyViewModelRedditTop( repositorio as RedditTopRepository) as T



            else -> throw IllegalArgumentException("View model class not Found")
        }


        }


}

