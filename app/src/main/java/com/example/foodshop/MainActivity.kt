package com.example.foodshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.foodshop.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.host_fragment) as NavHostFragment


        val navController = navHostFragment.navController

        binding.hostFragment
        binding.bottomNavView.setupWithNavController(navController)
    }
}