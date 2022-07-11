package com.aran.githubapp.model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aran.githubapp.api.Config
import com.aran.githubapp.data.dataset.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllUsersViewModel: ViewModel() {
    private var listUsers = MutableLiveData<ArrayList<Users>>()

    fun setAllUsers(context: Context) {
        Config.getService().getUsers().enqueue(object : Callback<ArrayList<Users>> {
            override fun onResponse(
                call: Call<ArrayList<Users>>,
                response: Response<ArrayList<Users>>
            ) {
                if (response.isSuccessful) {
                    listUsers.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<Users>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getAllUsers(): LiveData<ArrayList<Users>> {
        return listUsers
    }

    companion object {
        const val TAG = "TAG"
    }
}