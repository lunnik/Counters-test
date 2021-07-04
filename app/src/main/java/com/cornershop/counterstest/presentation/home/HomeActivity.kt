package com.cornershop.counterstest.presentation.home

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cornershop.counterstest.databinding.HomeActivityBinding

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