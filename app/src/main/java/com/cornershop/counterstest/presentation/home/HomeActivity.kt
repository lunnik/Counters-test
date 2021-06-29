package com.cornershop.counterstest.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.HomeActivityBinding
import com.cornershop.counterstest.presentation.home.HomeFragment

class HomeActivity : AppCompatActivity() {

    /* */
    private val binding: HomeActivityBinding
            by lazy { HomeActivityBinding.inflate(layoutInflater) }


    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    /** */
    override fun onNavigateUp(): Boolean {
        finish()
        return true
    }

}