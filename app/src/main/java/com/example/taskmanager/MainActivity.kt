package com.example.taskmanager

import Pref
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.taskmanager.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity(): AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val pref = Pref(this)
        val auth = FirebaseAuth.getInstance()
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        //  menu should be considered as top level destinations.

        if (pref.isOnBoardingSeen())
            navController.navigate(R.id.onBoardingFragment)

        if (auth.currentUser==null) {
            navController.navigate(R.id.authFragment)
        }

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.taskFragment
            )
        )
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = destination.id != R.id.taskFragment
            if (destination.id == R.id.onBoardingFragment  || destination.id == R.id.authFragment  ) {
                supportActionBar?.hide()
            }else supportActionBar?.show()

        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}