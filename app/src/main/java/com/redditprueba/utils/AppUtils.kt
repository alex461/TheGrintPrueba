package com.redditprueba.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.redditprueba.R
import com.redditprueba.view.fragment.HomeFragment






fun Fragment.handleApiError(
        failure: Resource.Failure,
        retry: (() -> Unit)?=null
    ){

        when{
            failure.isNetworkError -> requireView().snackBar(getString(R.string.error_conexion),retry)
            failure.errorCode == 401 ->{
                if (this is HomeFragment){
                    requireView().snackBar(getString(R.string.error401))

                }




            }else ->{
            val error = failure.errorBody?.toString().toString()
            requireView().snackBar(error)
        }

        }

    }





fun View.snackBar(message: String, action:(() -> Unit)? =null){

    val snackBar = Snackbar.make(this,message, Snackbar.LENGTH_LONG)
    action?.let {
        snackBar.setAction("Retry"){
            it()
        }
    }
    snackBar.show()

}


