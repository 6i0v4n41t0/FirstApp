package com.giovana.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.giovana.firstapp.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {

    //criar a toolbar(barra de cima de navegacao)
    private lateinit var appBarConfiguration: AppBarConfiguration
    //criar a navegacao
    private lateinit var navController: NavController

    //criar o binding(cabinho de ligacao da tela e a programacao)

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //configura o binding
        _binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configura a navegacao e a toolbar
        val navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

    }
}