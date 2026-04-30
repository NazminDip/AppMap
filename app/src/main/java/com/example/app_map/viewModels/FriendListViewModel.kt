package com.example.app_map.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_map.AppUsers
import com.example.app_map.Repo.UserRepository

class FriendListViewModel(private val repo: UserRepository)
    : ViewModel() {
    private val _userList = MutableLiveData<List<AppUsers>>()
    val userList: LiveData<List<AppUsers>> get() = _userList

    fun fetchUsers(){
        repo.getAllUsers { list ->
            _userList.postValue(list)
        }
    }
    //logout

    fun logOut(){
        repo.logOut()
    }

}