package com.example.mapapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope


import com.example.app_map.Repo.UserRepository
import kotlinx.coroutines.launch


class AuthViewModel(private val repo: UserRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val loginResult = MutableLiveData<Pair<Boolean, String?>>()
    val registerResult = MutableLiveData<Pair<Boolean, String?>>()

    //change
    fun login(email: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            repo.loginUser(email, password) { success, message ->

                isLoading.postValue(false)
                loginResult.postValue(Pair(success, message))
            }
        }


    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            isLoading.value = true
            repo.registerUser(email, password) { success, message ->
                isLoading.postValue(false)
                registerResult.postValue(Pair(success, message))

            }

        }

        }

}