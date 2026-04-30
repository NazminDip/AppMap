package com.example.app_map

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app_map.Repo.UserRepository
import com.example.app_map.databinding.ActivityAuthBinding
import com.example.mapapp.viewModels.AuthViewModel

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val repo = UserRepository()

    private val viewModel: AuthViewModel by viewModels<AuthViewModel> {
        object : ViewModelProvider.Factory {
            override fun < T : ViewModel> create(modelClass: Class<T>): T {
                return AuthViewModel(repo) as T
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {



            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                return@setOnClickListener
            }

            viewModel.login(email, password)

        }
        binding.btnRegister.setOnClickListener {

            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                return@setOnClickListener
            }

            viewModel.register(email, password)
        }
        //change


        // ✅ ProgressBar observer
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressCircular.visibility =
                if (isLoading) android.view.View.VISIBLE else android.view.View.GONE


        }
        viewModel.registerResult.observe(this) { (success, message) ->
            if (success) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginResult.observe(this) { (success, message) ->
            if (success) {
                Toast.makeText(this, "Logged In Successful", Toast.LENGTH_SHORT).show()
                navigateToFriendList()
            } else {
                Toast.makeText(this, "Logged In Failed", Toast.LENGTH_SHORT).show()
            }
        }


    }
//////    SHOW ALL USER

    private fun navigateToFriendList() {
        val intent = Intent(this, FriendListActivity::class.java)
        startActivity(intent)
        finish()
    }




}