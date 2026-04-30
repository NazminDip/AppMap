package com.example.app_map.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_map.AppUsers
import com.example.app_map.Repo.UserRepository


class MyProfileViewModel(private val repo: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<AppUsers?>()
    val user: LiveData<AppUsers?> get() = _user

    fun loadUser(userId: String) {
        repo.getUserById(userId) {
            _user.postValue(it)
        }
    }

    fun updateUsername(userId: String, newName: String, onResult: (Boolean) -> Unit) {
        repo.updateUsername(userId, newName) {
            onResult(it)
        }
    }
}